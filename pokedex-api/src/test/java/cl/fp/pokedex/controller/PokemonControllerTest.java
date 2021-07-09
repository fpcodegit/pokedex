package cl.fp.pokedex.controller;

import cl.fp.pokedex.domain.pokedex.Pokemon;
import cl.fp.pokedex.service.PokemonService;
import cl.fp.pokedex.url.builder.PokeApiUrlBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class PokemonControllerTest {
    @Mock
    private PokemonService pokemonService;
    @Mock
    private PokeApiUrlBuilder pokeApiUrlBuilder;
    @Mock
    private Pokemon pokemon;
    @InjectMocks
    private PokemonController pokemonController;

    @Test
    void shouldGetPokemonById() {
        final String id = "id";
        final String pokemonUrl = "pokemonUrl";
        given(pokeApiUrlBuilder.getPokemonWithId(id)).willReturn(pokemonUrl);
        given(pokemonService.getDetailPokemon(pokemonUrl)).willReturn(pokemon);

        Pokemon result = pokemonController.getPokemon(id);

        assertNotNull(result);
        assertEquals(pokemon, result);
    }

    @AfterEach
    void tearDown() {
        then(pokemonService).shouldHaveNoMoreInteractions();
        then(pokeApiUrlBuilder).shouldHaveNoMoreInteractions();
        then(pokemon).shouldHaveNoMoreInteractions();
    }
}
