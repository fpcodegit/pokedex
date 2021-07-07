package cl.fp.pokedex.step;

import cl.fp.pokedex.common.TestInformation;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.givenThat;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

public class PokemonSteps {
    @Autowired
    private TestInformation testInformation;

    @Given("^the pokeApi (.*) endpoint with id (.*) is available$")
    public void thePokeApiPokemonEndpointIsAvailable(String endpoint, String id) {
        givenThat(get(urlEqualTo("/api/v2/" + endpoint + "/" + id + "/"))
                .willReturn(ok()
                        .withHeader("content-type", "application/json")
                        .withBodyFile(endpoint + "/" + id + ".json")));
        givenThat(get(urlEqualTo("/api/v2/" + endpoint + "/" + id))
                .willReturn(ok()
                        .withHeader("content-type", "application/json")
                        .withBodyFile(endpoint + "/" + id + ".json")));
    }

    @When("^the pokemon endpoint with id (.*) is called$")
    public void thePokedexEndpointIsCalled(String id) {
        Response response = RestAssured.given().get("http://localhost:8080/pokemon/" + id);
        testInformation.addObject("response", response);
    }
}
