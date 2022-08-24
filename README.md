# kees/grip

<details open>
<summary>What's in each branch of this project?</summary>

 Branch | Description
 --- | ---
 `main` | Where all development occurs
 `001-stateless` | A decent little clickable grid (bad on mobile)
 `002-list-lookup-state` | Grid toggle is refactored to use state
 `003-bool-array-state` | Grid state is improved

</details>

Develop on the project on any given branch:

```sh
npm i
npm run watch
open http://localhost:8280/
```

The project is not meant to be built manually but it can be done with `npm run release`.

---

I am working on a unique CI/CD workflow using branches instead of releases. All development occurs on `main`. A branch is created at any milestone which will publish IN PARALLEL to all other branches. I use numerical prefixes which is ultimately arbitrary.

Currently I need to work on custom formatting indices and page navigation, then the effect will work. I want a visitor to step through live builds to see the progress and if interested, read my thoughts on them. Some ideas are using a template parser (i.e [selmer, included in babashka](https://book.babashka.org/#built-in-namespaces), which could be run from the repo or [in an action](https://github.com/marketplace/actions/setup-clojure)), or scraping the existing live pages and forcing in new dom nodes.

**For now, view the main branch here:** [hardly.link/projects/d02-grip/](https://hardly.link/projects/d02-grip/)

---

<details>
<summary>View the loose initial roadmap</summary>

> - :books: Section
> - :green_book: Subsection
> - Development goal
> - :pencil2: In progress
> - :eight_pointed_black_star: Completed and unreleased
> - :white_check_mark: Completed and released

What is this list formatting? I’m trying out a style of list [I wrote about adapting here](https://www.are.na/block/17704579). It's an informal and currently manual notation for managing projects that are too small to need some grand kanban deck.

---

- :books: Housekeeping
	- :pencil2: Add some notes
		- :white_check_mark: Basic information
		- Mindset and goals
- :white_check_mark: Basic statefulness
	- :white_check_mark: High-low state values
	- :white_check_mark: Boolean array state values
- :books: Schema/spec
	- Introduce something for validation (I like [malli](https://github.com/metosin/malli))
- :books: CI/CD
	- :pencil2: Branch or tag repo management?
	- :green_book: Build workflow
		- :pencil2: Create post-build script
			- :white_check_mark: Normal build
			- :white_check_mark: Accept a string argument that will propagate
			- :white_check_mark: Either switch HTML refs to relative paths OR just watch reloads
			- :white_check_mark: Upload build to SPECIFIC path of S3 bucket, deleting
			- :white_check_mark: Invalidate CloudFront
			- Integrate `bb` to start redoing the primitive versions in clj scripts
			- :pencil2: Complete pagination
				- :green_book: Create index page linking to all published pages
					- Versions (e.g `003`)
					- Variants (e.g `003a`)
				- :pencil2: Create back and next buttons
			- :green_book: Further dom population
				- Insert github link to repo branch
				- Basic info "built from xxx in user/repo"
				- Page title from branch name?
			- Upload index to SPECIFIC path of S3 bucket, file only
		- :pencil2: Create github action
			- :white_check_mark: Detect some change (branch, tag)
			- Execute the post-build script with necessary data
		- Revise early published versions once workflow completed
- :books: Malleability
	- :green_book: Refine data transformations
		- Introduce [specter](https://github.com/redplanetlabs/specter) for incoming application of rules
	- :green_book: Prepare for data transformation
		- Introduce QOL form creation library ([fork](https://github.com/luciodale/fork)?)
	- Data in
	- Data out
	- Data transforms
- :books: Evolution
	- Stepwise re-render
	- Tick

</details>
