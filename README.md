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

Currently I need to work on custom formatting indices and page navigation, then the effect will work. I want a visitor to step through live buils to see the progress and if interested, read my thoughts on them. Some ideas are using a template parser (i.e [selmer, included in babashka](https://book.babashka.org/#built-in-namespaces), which could be run from the repo or [in an action](https://github.com/marketplace/actions/setup-clojure)), or scraping the existing live pages and forcing in new dom nodes.

For now, view the main branch here: [hardly.link/projects/d02-grip/](https://hardly.link/projects/d02-grip/)

---

<details>
<summary>View the loose initial roadmap</summary>

> - <mark style="background: #FFB8EBA6;">Section</mark> 
> - <mark style="background: #FF5582A6;">Subsection</mark> 
> - Development goal
> - <mark style="background: #FFB86CA6;">In progress</mark> 
> - <mark style="background: #FFF3A3A6;">Completed and unreleased</mark> 
> - <mark style="background: #BBFABBA6;">Completed and released</mark> 

What is this list formatting? I’m trying out a style of list [I wrote about adapting here](https://www.are.na/block/17704579). It's an informal and currently manual notation for managing projects that are too small to need some grand kanban deck.

---

- <mark style="background: #FFB8EBA6;">Housekeeping</mark> 
	- <mark style="background: #FFB86CA6;">Add some notes</mark> 
		- <mark style="background: #BBFABBA6;">Basic information</mark> 
		- Mindset and goals
- <mark style="background: #FFB8EBA6;">Statefulness</mark> 
	- <mark style="background: #BBFABBA6;">High-low state values</mark> 
	- <mark style="background: #FFF3A3A6;">Boolean array state values</mark> 
- <mark style="background: #FFB8EBA6;">Schema/spec</mark> 
	- Introduce something for validation
- <mark style="background: #FFB8EBA6;">CI/CD</mark> 
	- <mark style="background: #FFB86CA6;">Branch or tag repo management?</mark> 
	- <mark style="background: #FF5582A6;">Build workflow</mark> 
		- Create index page
		- <mark style="background: #FFB86CA6;">Create post-build script</mark> 
			- <mark style="background: #BBFABBA6;">Normal build</mark> 
			- <mark style="background: #BBFABBA6;">Accept a string argument that will propagate</mark> 
			- Update index page
			- <mark style="background: #BBFABBA6;">Either switch HTML refs to relative paths OR just watch reloads</mark> 
			- <mark style="background: #FFB86CA6;">Create back and next buttons</mark> 
			- <mark style="background: #BBFABBA6;">Upload build to SPECIFIC path of S3 bucket, deleting</mark> 
			- Upload index to SPECIFIC path of S3 bucket, file only
			- <mark style="background: #BBFABBA6;">Invalidate CloudFront</mark> 
		- <mark style="background: #FFB86CA6;">Create github action</mark> 
			- <mark style="background: #BBFABBA6;">Detect some change (branch, tag)</mark> 
			- Execute the post-build script with necessary data
- <mark style="background: #FFB8EBA6;">Malleability</mark> 
	- Data in
	- Data out
	- Data transforms
- <mark style="background: #FFB8EBA6;">Evolution</mark> 
	- Stepwise re-render
	- Tick

</details>
