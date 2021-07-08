package cl.fp.pokedex.controller;

import cl.fp.pokedex.domain.pokedex.Pokedex;
import cl.fp.pokedex.service.PokedexService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@ExtendWith(MockitoExtension.class)
class PokedexControllerTest {
    private static final Integer DEFAULT_LIMIT = 10;
    private static final Integer DEFAULT_OFFSET = 0;
    @Mock
    private PokedexService pokedexService;
    @Mock
    private Pokedex pokedex;
    @InjectMocks
    private PokedexController pokedexController;

    @BeforeEach
    void setUp() {
        setField(pokedexController, "defaultLimit", DEFAULT_LIMIT);
        setField(pokedexController, "defaultOffset", DEFAULT_OFFSET);
    }

    @Test
    void shouldGetPokedexWithLimitAndOffsetDefaultValues() {
        given(pokedexService.getPokedex(DEFAULT_LIMIT, DEFAULT_OFFSET)).willReturn(pokedex);

        Pokedex result = pokedexController.getPokedex(empty(), empty());

        assertNotNull(result);
        assertEquals(pokedex, result);
    }

    @Test
    void shouldGetPokedexWithLimitDefaultValuesAndOffsetProvided() {
        final Integer offset = 5;
        given(pokedexService.getPokedex(DEFAULT_LIMIT, offset)).willReturn(pokedex);

        Pokedex result = pokedexController.getPokedex(empty(), of(offset));

        assertNotNull(result);
        assertEquals(pokedex, result);
    }

    @Test
    void shouldGetPokedexWithLimitProvidedAndOffsetDefaultValue() {
        final Integer limit = 20;
        given(pokedexService.getPokedex(limit, DEFAULT_OFFSET)).willReturn(pokedex);

        Pokedex result = pokedexController.getPokedex(of(limit), empty());

        assertNotNull(result);
        assertEquals(pokedex, result);
    }

    @Test
    void shouldGetPokedexWithLimitAndOffsetProvided() {
        final Integer limit = 20;
        final Integer offset = 5;
        given(pokedexService.getPokedex(limit, offset)).willReturn(pokedex);

        Pokedex result = pokedexController.getPokedex(of(limit), of(offset));

        assertNotNull(result);
        assertEquals(pokedex, result);
    }

    @AfterEach
    void tearDown() {
        then(pokedexService).shouldHaveNoMoreInteractions();
        then(pokedex).shouldHaveNoMoreInteractions();
    }
}
