Feature: Pokemon feature

  Scenario: Pokemon endpoint return full detail of the pokemon
    Given the pokeApi pokemon endpoint with id 1 is available
    And the pokeApi pokemon-species endpoint with id 1 is available
    And the pokeApi evolution-chain endpoint with id 1 is available
    When the pokemon endpoint with id 1 is called
    Then the response status code is 200 and the response of type Pokemon is in the bulbasaur file
