package cl.fp.pokedex;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = PokedexApplication.class)
public class SpringIntegrationTest {
}
