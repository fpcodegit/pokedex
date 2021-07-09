package cl.fp.pokedex.link.builder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.Link;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class PokemonLinkBuilderTest {
    @InjectMocks
    private PokemonLinkBuilder pokemonLinkBuilder;

    @Test
    void shouldGetSelfLink() {
        final Integer pokemonId = 1;

        Link result = pokemonLinkBuilder.getSelfLink(pokemonId);

        assertNotNull(result);
        assertEquals("self", result.getRel().value());
        assertEquals("/pokemon/" + pokemonId, result.getHref());
    }
}
