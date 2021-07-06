Feature: Guess the word

  Scenario: Maker starts a game
    Given the game is setup
    When the Maker starts a game
    Then the Maker waits for a Breaker to join