package cl.fp.pokedex.step;

import cl.fp.pokedex.domain.pokedex.Pokedex;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.givenThat;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SampleSteps {
    @Autowired
    private ObjectMapper objectMapper;

    private Response response;

    @Given("^the pokeApi pokemon endpoint is available$")
    public void thePokeApiPokemonEndpointIsAvailable() {
        givenThat(get(urlEqualTo("/api/v2/pokemon"))
                .withQueryParam("limit", equalTo("10"))
                .withQueryParam("offset", equalTo("0"))
                .willReturn(ok().withBodyFile("pokemon-offset-0-limit-10.json")));
    }

    @When("^the pokedex endpoint is called$")
    public void thePokedexEndpointIsCalled() {
        response = RestAssured.given().get("http://localhost:8080/pokedex");
        response.prettyPrint();
    }

    @Then("^the response status code is (\\d+) and the response is (.*) file$")
    public void theResponseStatusCodeIsAndTheResponseIsFile(int statusCode, String fileName) throws IOException {
        assertEquals(statusCode, response.statusCode());
        byte[] bytes = Files.readAllBytes(Paths.get("./src/test/resources/responses/" + fileName + ".json"));
        Pokedex expectedPokedex = objectMapper.readValue(bytes, Pokedex.class);
        Pokedex actualPokedex = response.body().as(Pokedex.class);
        assertEquals(expectedPokedex, actualPokedex);
    }
}
