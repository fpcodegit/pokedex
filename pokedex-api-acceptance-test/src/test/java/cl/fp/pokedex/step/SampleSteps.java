package cl.fp.pokedex.step;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SampleSteps {
    @Given("the game is setup")
    public void setup() {
        // execute step
    }

    @When("the Maker starts a game")
    public void someStep() {
        // execute step
    }

    @Then("the Maker waits for a Breaker to join")
    public void otherStep() {
        // execute other step
    }
}
