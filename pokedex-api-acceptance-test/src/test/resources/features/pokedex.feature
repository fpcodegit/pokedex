Feature: Pokedex feature

  Scenario: Pokedex endpoint return list of pokemon and basic information
    Given the pokeApi pokemon endpoint is available
    When the pokedex endpoint is called
    Then the response status code is 200 and the response is pokedex file