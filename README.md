# Pokedex API
### Spring boot application to expose Pokedex API consumed from https://pokeapi.co

###### Config Server Code: https://github.com/fpcodegit/config-server-pokedex
###### Front end Code: https://github.com/fpcodegit/fp-pokedex-svelte
###### Central configuration repository: https://github.com/fpcodegit/config-server-repo

This API exposes 2 endpoints to provide the required functionality:
1. ***/pokedex*** Expose a list of Pokemon with details.
    1. Support *limit* and *offset* as query parameters to control the output of the response.
        1. *limit* How many results will be included in the list.
        1. *offset* How many item will be skipped.
    1. Sample response with limit 1 and offset 0
    ```json
    {
      "pokemons": [
        {
          "name": "bulbasaur",
          "imageUrl": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
          "types": [
            "grass",
            "poison"
          ],
          "weight": 69,
          "abilities": [
            "overgrow",
            "chlorophyll"
          ],
          "description": "A strange seed was\nplanted on its\nback at birth.\fThe plant sprouts\nand grows with\nthis POKéMON.",
          "evolutions": [
            "bulbasaur",
            "ivysaur",
            "venusaur"
          ],
          "links": [
            {
              "rel": "self",
              "href": "https://localhost:8080/pokemon/1"
            }
          ]
        }
      ],
      "links": [
        {
          "rel": "self",
          "href": "https://localhost:8080/pokedex?limit=1&offset=0"
        },
        {
          "rel": "prev",
          "href": "https://localhost:8080/pokedex?limit=1&offset=0"
        },
        {
          "rel": "next",
          "href": "https://localhost:8080/pokedex?limit=1&offset=1"
        }
      ]
    }
   ```
1. ***/pokemon/{pokemonId}*** Expose a Pokemon with his details.
    1. The ***pokemonId*** is part of the url and is the only parameter that support this endpoint.
    1. Sample response with ***pokemonId*** 1.
    ```json
   {
     "name": "bulbasaur",
     "imageUrl": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
     "types": [
       "grass",
       "poison"
     ],
     "weight": 69,
     "abilities": [
       "overgrow",
       "chlorophyll"
     ],
     "description": "A strange seed was\nplanted on its\nback at birth.\fThe plant sprouts\nand grows with\nthis POKéMON.",
     "evolutions": [
       "bulbasaur",
       "ivysaur",
       "venusaur"
     ],
     "links": [
       {
         "rel": "self",
         "href": "https://localhost:8080/pokemon/1"
       }
     ]
   }
    ```
   
#### Error handling
There is not a dedicated error handling logic, just using the default provided by SpringBoot, that will return a 
response like this in case of any error happens:
```json
{
  "timestamp": "2021-07-01T18:09:44.104+00:00", 
  "status": 500,
  "error": "Internal Server Error",
  "exception": "org.springframework.web.client.HttpClientErrorException$NotFound",
  "message": "404 Not Found: [{\n}]",
  "path": "/pokemon/0"
}
```

#### Cache
The applications use cache for the external API calls, currently uses a simple on memory cache that is the default 
implementation for *'org.springframework.boot:spring-boot-starter-cache'*. 
The cache is clean when the application starts.

#### Testing
1. Unit tests: For unit tests in the applications Junit5 and mockito are used. **Located in 
*pokedex-api/src/test/java/cl.fp.pokedex***

2. Cucumber test: This test the whole application as a unit, passing an input and checking the expected answer with 
the actual response of the application. To provide an alternative to call the real external API, wiremock was used to 
provide stubs for the external API. Cucumber, Junit5, wiremock and RestAssured were used to achieve this testing.
**Located in *pokedex-api-acceptance-test/src/test/resources/features***

#### Useful commands
1. **./gradlew** run the default task ('clean', 'build', 'cucumberCli') in the specified order.
1. **./gradlew build** create the executable jar (*pokedex-api/build/libs/pokedex-api-0.0.1.jar*) and run unit tests.
1. **./gradlew test** run only unit tests.
1. **./gradlew cucumberCli** run only cucumber tests.
1. **./gradlew bootRun** starts the application from command line.

---

##### Deploy instructions
###### Heroku:
1. As the project is gradle based, it required some extra configuration in the build.gradle, the task stage was add
 to work with the Heroku deployment steps.
    ```groovy
    task stage(dependsOn: ['bootJar']) {
        doLast {
            'bootJar'
        }
    }
    ```
1. As the platform requirements we need to specify that we have a web type project, so we create a ***Procfile*** 
with the information. The format is ```<type>: command to run the application```
1. In Heroku website:
    1. Access your account.
    1. Create a new app.
    1. Connect the app to Github repository.
    1. Use manual deploy to deploy the current state of the master branch 
    (remember to have this branch in a running state)

Adding the gradle task and creating the Procfile is one time task only.
