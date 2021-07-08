Feature: Pokedex feature

  Scenario: Pokedex endpoint return list of pokemon and basic information
    Given the pokeApi pokemon endpoint is available
    And the pokeApi pokemon endpoint with id 1 is available with 200 status
    And the pokeApi pokemon endpoint with id 2 is available with 200 status
    And the pokeApi pokemon endpoint with id 3 is available with 200 status
    And the pokeApi pokemon endpoint with id 4 is available with 200 status
    And the pokeApi pokemon endpoint with id 5 is available with 200 status
    And the pokeApi pokemon endpoint with id 6 is available with 200 status
    And the pokeApi pokemon endpoint with id 7 is available with 200 status
    And the pokeApi pokemon endpoint with id 8 is available with 200 status
    And the pokeApi pokemon endpoint with id 9 is available with 200 status
    And the pokeApi pokemon endpoint with id 10 is available with 200 status
    When the pokedex endpoint is called
    Then the response status code is 200 and the response of type Pokedex is in the pokedex file
