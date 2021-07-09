package cl.fp.pokedex.link.builder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.Link;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class PokedexLinkBuilderTest {
    @InjectMocks
    private PokedexLinkBuilder pokedexLinkBuilder;

    @Test
    void shouldGetSelfLink() {
        final Integer limit = 10;
        final Integer offset = 0;

        Link result = pokedexLinkBuilder.getSelfLink(limit, offset);

        assertNotNull(result);
        assertEquals("self", result.getRel().value());
        assertEquals("/pokedex?limit=" + limit + "&offset=" + offset, result.getHref());
    }

    @Test
    void shouldGetNextLink() {
        final Integer limit = 10;
        final Integer offset = 20;

        Link result = pokedexLinkBuilder.getNextLink(limit, offset);

        assertNotNull(result);
        assertEquals("next", result.getRel().value());
        assertEquals("/pokedex?limit=" + limit + "&offset=" + (offset + limit), result.getHref());
    }

    @Test
    void shouldGetSamePrevLinkWhenOffsetIs0() {
        final Integer limit = 10;
        final Integer offset = 0;

        Link result = pokedexLinkBuilder.getPrevLink(limit, offset);

        assertNotNull(result);
        assertEquals("prev", result.getRel().value());
        assertEquals("/pokedex?limit=" + limit + "&offset=" + offset, result.getHref());
    }

    @Test
    void shouldGetPrevLinkWhenOffsetIsNot0() {
        final Integer limit = 10;
        final Integer offset = 30;

        Link result = pokedexLinkBuilder.getPrevLink(limit, offset);

        assertNotNull(result);
        assertEquals("prev", result.getRel().value());
        assertEquals("/pokedex?limit=" + limit + "&offset=" + (offset - limit), result.getHref());
    }
}
