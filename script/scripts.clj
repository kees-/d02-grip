(ns scripts
  (:require [pod.babashka.aws :as aws]
            [pod.babashka.aws.credentials :as cred]
            [clojure.edn :as edn]
            [clojure.tools.cli :as cli]
            [clojure.string :as s]
            [selmer.parser :as selmer]))

(def cli-opts
  [["-a" "--aki ID" "Access Key ID"]
   ["-s" "--sak KEY" "Secret Access Key"]
   ["-r" "--region REGION" "AWS Region"]
   ["-b" "--bucket BUCKET" "AWS Bucket"]
   ["-p" "--branch-prefix PREFIX" "Current Branch Prefix"]
   ["-n" "--repo-name REPO" "Unqualified Repo Name"]])

(defn branch-prefix-format
  [s]
  (re-seq #"\d+|\D+" s))

(defn- client
  [{:keys [aki sak region]}]
  (aws/client {:api :s3
               :credentials-provider (cred/basic-credentials-provider
                                      {:access-key-id aki
                                       :secret-access-key sak
                                       :region region})}))

(defn live-branches
  [edn]
  (let [{:keys [bucket repo-name] :as opts} edn
        s3 (client opts)]
    (->> {:op :ListObjectsV2
          :request {:Bucket bucket
                    :Prefix (str "projects/" repo-name "/")
                    :Delimiter "/"}}
         (aws/invoke s3)
         :CommonPrefixes
         (mapv (comp
                last
                #(s/split % #"/")
                :Prefix)))))

(defn write-index-page
  [& args]
  (let [{:keys [branch-prefix] :as edn} (-> *command-line-args*
                                     (cli/parse-opts cli-opts)
                                     :options)
        mains (live-branches edn)
        mains-ordered (->> (conj mains branch-prefix)
                           (map (comp first
                                      branch-prefix-format))
                           (remove #(= \_ (first %)))
                           set
                           sort
                           reverse)]
    (spit "resources/public/grip.html"
          (selmer/render-file
           "grip.html"
           {:MAINS mains-ordered
            :LATEST (first mains-ordered)}))))

;; bb run render-index-page '{:aki "" :sak "" :region "" :bucket "" :branch "" :repo ""}'
