Feature: Pokemon feature

  Scenario: Pokemon endpoint return full detail of the pokemon
    Given the pokeApi pokemon endpoint with id 1 is available with 200 status
    And the pokeApi pokemon-species endpoint with id 1 is available with 200 status
    And the pokeApi evolution-chain endpoint with id 1 is available with 200 status
    When the pokemon endpoint with id 1 is called
    Then the response status code is 200 and the response of type Pokemon is in the bulbasaur file

  Scenario: Pokemon endpoint return error when the pokeApi returns 404 on resource
    Given the pokeApi pokemon endpoint with id 0 is available with 404 status
    When the pokemon endpoint with id 0 is called
    Then the response status code is 500 and the response of type Error is in the error file
