package cl.fp.pokedex.client;

import cl.fp.pokedex.domain.pokedex.Pokedex;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class RestTemplatePokeApiClientTest {
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private Pokedex pokedex;
    @InjectMocks
    private RestTemplatePokeApiClient restTemplatePokeApiClient;

    @Test
    void shouldGetResourceFromTheProvidedClass() {
        final String url = "url";
        final Class<Pokedex> pokedexClass = Pokedex.class;
        given(restTemplate.getForObject(url, pokedexClass)).willReturn(pokedex);

        Pokedex result = restTemplatePokeApiClient.getResource(url, pokedexClass);

        assertNotNull(result);
        assertEquals(pokedex, result);
    }

    @AfterEach
    void tearDown() {
        then(restTemplate).shouldHaveNoMoreInteractions();
        then(pokedex).shouldHaveNoMoreInteractions();
    }
}
