package cl.fp.pokedex.url.builder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@ExtendWith(MockitoExtension.class)
class PokeApiUrlBuilderTest {
    private static final String API_BASE_URL = "http://localhost:8080/api/";
    @InjectMocks
    private PokeApiUrlBuilder pokeApiUrlBuilder;

    @BeforeEach
    void setUp() {
        setField(pokeApiUrlBuilder, "apiBaseUrl", API_BASE_URL);
    }

    @Test
    void shouldGetListWithLimitAndOffset() {
        final Integer limit = 10;
        final Integer offset = 0;

        String result = pokeApiUrlBuilder.getListWithLimitAndOffset(limit, offset);

        assertNotNull(result);
        assertEquals(API_BASE_URL + "?limit=" + limit + "&offset=" + offset, result);
    }

    @Test
    void shouldGetPokemonWithId() {
        final String id = "id";

        String result = pokeApiUrlBuilder.getPokemonWithId(id);

        assertNotNull(result);
        assertEquals(API_BASE_URL + id, result);
    }
}
