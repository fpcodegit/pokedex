package cl.fp.pokedex.step;

import cl.fp.pokedex.common.TestInformation;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.givenThat;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;

public class PokedexSteps {
    @Autowired
    private TestInformation testInformation;

    @Given("^the pokeApi pokemon endpoint is available$")
    public void thePokeApiPokemonEndpointIsAvailable() {
        givenThat(get(urlPathEqualTo("/api/v2/pokemon/"))
                .withQueryParam("limit", equalTo("10"))
                .withQueryParam("offset", equalTo("0"))
                .willReturn(ok()
                        .withHeader("content-type", "application/json")
                        .withBodyFile("pokemon-offset-0-limit-10.json")));
    }

    @When("^the pokedex endpoint is called$")
    public void thePokedexEndpointIsCalled() {
        Response response = RestAssured.given().get("http://localhost:8080/pokedex");
        testInformation.addObject("response", response);
    }
}
