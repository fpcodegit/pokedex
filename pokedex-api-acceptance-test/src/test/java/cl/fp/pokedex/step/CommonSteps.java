package cl.fp.pokedex.step;

import cl.fp.pokedex.common.TestInformation;
import cl.fp.pokedex.domain.Error;
import cl.fp.pokedex.domain.pokedex.Pokedex;
import cl.fp.pokedex.domain.pokedex.Pokemon;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CommonSteps {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TestInformation testInformation;

    @Then("^the response status code is (\\d+) and the response of type (.*) is in the (.*) file$")
    public void theResponseStatusCodeIsAndTheResponseOfTypeIsInTheFile(int statusCode, String type, String fileName) throws IOException {
        Response response = testInformation.getObjectOfType("response", Response.class);
        assertEquals(statusCode, response.statusCode());
        byte[] bytes = Files.readAllBytes(Paths.get("./src/test/resources/responses/" + fileName + ".json"));
        Class typeOfResponse = null;
        if ("Pokedex".equals(type)) {
            typeOfResponse = Pokedex.class;
        }
        if ("Pokemon".equals(type)) {
            typeOfResponse = Pokemon.class;
        }
        if ("Error".equals(type)) {
            typeOfResponse = Error.class;
        }
        assertNotNull(typeOfResponse);
        Object expectedResponse = objectMapper.readValue(bytes, typeOfResponse);
        Object actualResponse = response.body().as(typeOfResponse);
        if ("Error".equals(type)) {
            assertNotNull(((Error) actualResponse).getTimestamp());
            ((Error) actualResponse).setTimestamp(null);
        }
        assertEquals(expectedResponse, actualResponse);
    }
}
