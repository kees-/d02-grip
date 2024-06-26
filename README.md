# kees/grip

https://user-images.githubusercontent.com/6820950/189215425-957c72d6-dc08-431e-863b-8d3fda5d242e.mp4

An XOR gate with inputs at $7,0$ and $7,1$ outputting at $5,0$.

<details open>
<summary>What's in each branch of this project?</summary>

 Branch | Description
 --- | ---
 `main` | Where all development occurs
 `001-stateless` | A decent little clickable grid (bad on mobile)
 `002-list-lookup-state` | Grid toggle is refactored to use state
 `003-bool-array-state` | Grid state is improved
 `004-control-panel` | Input panels for grid interaction
 `005-ruleset` | Apply a stateful series of logic gates

</details>

---

<details>
<summary>Develop on the project on any given branch:</summary>

```sh
npm i
npm run watch
open http://localhost:8280/
```

The project is not meant to be built manually but it can be done with `npm run release`.

</details>

---

I am working on a unique CI/CD workflow using branches instead of releases. All development occurs either on `main` or a local branch. A branch is pushed at any milestone which will publish IN PARALLEL to all other branches. I use numerical prefixes which is arbitrary but conveniently sorts chronologically.

Currently I need to work on custom formatting indices and page navigation, then the effect will work. I want a visitor to step through live builds to see the progress and if interested, read thoughts on them. Realistically the solution will be a canvas page that uses `rewrite-clj` on publish and embeds live builds in an iframe.

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

What is this list formatting? I’m trying out a style of list [I wrote about adapting here](https://www.are.na/block/17704579). It's an informal and currently manual notation for managing projects that are too small to need a grand kanban deck.

---

- :books: Housekeeping
	- :pencil2: Add some notes
		- :white_check_mark: Basic information
		- Mindset and goals
- :books: Important fixes
	- :pencil2: Mobile layout
- :white_check_mark: Basic statefulness
	- :white_check_mark: High-low state values
	- :white_check_mark: Boolean array state values
		- :white_check_mark: Fix XY swapping
		- :white_check_mark: Fix integer coercion with interceptor
- :books: Schema/spec
	- Introduce something for validation (I like [malli](https://github.com/metosin/malli)?)
- :books: CI/CD
	- :white_check_mark: Branch or tag repo management?
	- :pencil2: Build workflow
		- :pencil2: Create post-build script
			- :white_check_mark: Normal build
			- :white_check_mark: Accept a string argument that will propagate
			- :white_check_mark: Either switch HTML refs to relative paths OR just watch reloads
			- :white_check_mark: Upload build to SPECIFIC path of S3 bucket, deleting
			- :white_check_mark: Upload index to SPECIFIC path of S3 bucket, file only
			- :white_check_mark: Invalidate CloudFront
			- :white_check_mark: Integrate `bb` to start redoing the primitive versions in clj scripts
			- :pencil2: Complete pagination
				- :pencil2: Create index page linking to all published pages
					- :white_check_mark: Versions (e.g `003`)
					- Variants (e.g `003a`)
				- :pencil2: Create back and next buttons
					- *rewrite-clj to add vector of all populated sorted branches to views and dynamically choose current +1 and -1*
			- :green_book: Further dom population
				- Insert github link to repo branch
				- Basic info "built from xxx in user/repo"
				- Page title from branch name?
			- Style index page
		- :white_check_mark: Create github action
			- :white_check_mark: Detect some change (branch, tag)
			- :white_check_mark: Execute the post-build script with necessary data
		- :pencil2: Revise early published versions once workflow completed
- :books: Malleability
	- :green_book: Refine data transformations
		- Introduce [specter](https://github.com/redplanetlabs/specter) for incoming application of rules?
	- :green_book: Prepare for data transformation
		- :white_check_mark: Introduce QOL form creation library ([fork](https://github.com/luciodale/fork)?)
		- Explore alternatives for simplicity
	- :pencil2: Data in
		- :white_check_mark: Control panel
		- :white_check_mark: Basic actions
		- Continue with basic input actions as needed
		- Read from saved grid and ruleset: program abstraction
	- :pencil2: Data out
		- :white_check_mark: Single button status
		- Side effects
		- Save state and rules as accessible program
	- Data transforms
		- :pencil2: Logic gates
			- :white_check_mark: Basic unary & $n$-ary gates
			- XOR, XNOR
		- Logic gate status lights, styling, diagrams
- :books: Evolution
	- Stepwise re-render, time
	- :white_check_mark: Tick

(archival)

- Mouse tools
	- Bit flip
	- Bit sel
	- Chip place
- Hover
	- Chip coordinates
- Creation QOL
	- Edit
	- Delete
	- Reorder
- Abstraction
	- Save
		- Preserve
			- Default state
			- Ordered ruleset
		- Define
			- In targets
	- Use
		- Select a preserve
		- Define points of connection (i/o targets)
		- Invisible logic implementation
		- Output notes
- Console
- Sfx
	- High
	- Medium

</details>
