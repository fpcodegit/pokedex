package cl.fp.pokedex;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

@CucumberContextConfiguration
@SpringBootTest(classes = PokedexApplication.class)
@AutoConfigureWireMock
public class SpringIntegrationTest {
}
