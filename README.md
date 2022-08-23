# kees/grip

What is each branch of this project?

 Branch | Description 
 --- | ---
 `main` | Where all development occurs
 `000-stateless` | A decent little clickable grid (bad on mobile)

Develop on the project on any given branch:

```sh
npm i
npm run watch
open http://localhost:8280/
```

The project is not meant to be built manually but it can be done with `npm run release`.

I am working on a CI/CD workflow where ALL branches are published as adjacent projects online to view their progression.

For now, view the first one here: [hardly.link/projects/d02-grip/](https://hardly.link/projects/d02-grip/)
