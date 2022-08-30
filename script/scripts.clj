(ns scripts
  (:require [pod.babashka.aws :as aws]
            [pod.babashka.aws.credentials :as cred]
            [babashka.fs :as fs]
            [clojure.edn :as edn]
            [clojure.string :as s]
            [selmer.parser :as selmer]))

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
  (let [{:keys [bucket] :as opts} edn
        s3 (client opts)]
    (->> {:op :ListObjectsV2
          :request {:Bucket bucket
                    :Prefix "projects/d02-grip/"
                    :Delimiter "/"}}
         (aws/invoke s3)
         :CommonPrefixes
         (mapv (comp
                last
                #(s/split % #"/")
                :Prefix)))))

(defn write-index-page
  [arg-map]
  (let [{:keys [branch] :as edn} (edn/read-string arg-map)
        mains (live-branches edn)
        mains-ordered (->> (conj mains branch)
                           (map (comp first
                                      branch-prefix-format))
                           set
                           sort
                           reverse)]
    (spit "release/grip.html" #_"gripindex.html"
          (selmer/render-file
           (str "release/" branch "/grip.html") #_"grip.html"
           {:MAINS mains-ordered
            :LATEST (first mains-ordered)}))))

;; bb run render-index-page '{:aki "" :sak "" :region "" :bucket "" :branch ""}'
