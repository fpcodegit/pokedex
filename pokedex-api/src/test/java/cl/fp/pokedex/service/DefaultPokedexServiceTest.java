package cl.fp.pokedex.service;

import cl.fp.pokedex.client.PokeApiClient;
import cl.fp.pokedex.domain.poke.api.NamedApiResource;
import cl.fp.pokedex.domain.poke.api.PokemonList;
import cl.fp.pokedex.domain.pokedex.Pokedex;
import cl.fp.pokedex.domain.pokedex.Pokemon;
import cl.fp.pokedex.link.builder.PokedexLinkBuilder;
import cl.fp.pokedex.url.builder.PokeApiUrlBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.Link;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class DefaultPokedexServiceTest {
    @Mock
    private PokeApiClient pokeApiClient;
    @Mock
    private PokemonService pokemonService;
    @Mock
    private PokedexLinkBuilder pokedexLinkBuilder;
    @Mock
    private PokeApiUrlBuilder pokeApiUrlBuilder;
    @Mock
    private PokemonList pokemonList;
    @Mock
    private NamedApiResource namedApiResource;
    @Mock
    private Pokemon pokemon;
    @Mock
    private Link link;
    @InjectMocks
    private DefaultPokedexService defaultPokedexService;

    @Test
    void shouldGetPokedex() {
        final Integer limit = 10;
        final Integer offset = 0;
        final String pokemonListUrl = "pokemonListUrl";
        given(pokeApiUrlBuilder.getListWithLimitAndOffset(limit, offset)).willReturn(pokemonListUrl);
        given(pokeApiClient.getResource(pokemonListUrl, PokemonList.class)).willReturn(pokemonList);
        final List<NamedApiResource> namedApiResources = asList(namedApiResource, namedApiResource);
        given(pokemonList.getResults()).willReturn(namedApiResources);
        final String pokemonUrl = "pokemonUrl";
        given(namedApiResource.getUrl()).willReturn(pokemonUrl);
        given(pokemonService.getDetailPokemon(pokemonUrl)).willReturn(pokemon);
        given(pokedexLinkBuilder.getSelfLink(limit, offset)).willReturn(link);
        given(pokedexLinkBuilder.getPrevLink(limit, offset)).willReturn(link);
        given(pokedexLinkBuilder.getNextLink(limit, offset)).willReturn(link);

        Pokedex result = defaultPokedexService.getPokedex(limit, offset);

        assertNotNull(result);
        final List<Pokemon> expectedPokemonList = asList(pokemon, pokemon);
        assertIterableEquals(expectedPokemonList, result.getPokemons());
        final List<Link> expectedLinksList = asList(link, link, link);
        assertIterableEquals(expectedLinksList, result.getLinks());
    }

    @AfterEach
    void tearDown() {
        then(pokeApiClient).shouldHaveNoMoreInteractions();
        then(pokemonService).shouldHaveNoMoreInteractions();
        then(pokedexLinkBuilder).shouldHaveNoMoreInteractions();
        then(pokeApiUrlBuilder).shouldHaveNoMoreInteractions();
        then(pokemonList).shouldHaveNoMoreInteractions();
        then(namedApiResource).shouldHaveNoMoreInteractions();
        then(pokemon).shouldHaveNoMoreInteractions();
        then(link).shouldHaveNoMoreInteractions();
    }
}
