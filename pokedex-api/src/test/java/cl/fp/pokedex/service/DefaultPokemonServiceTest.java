package cl.fp.pokedex.service;

import cl.fp.pokedex.client.PokeApiClient;
import cl.fp.pokedex.domain.poke.api.ApiResource;
import cl.fp.pokedex.domain.poke.api.EvolutionChain;
import cl.fp.pokedex.domain.poke.api.FlavorTextEntry;
import cl.fp.pokedex.domain.poke.api.NamedApiResource;
import cl.fp.pokedex.domain.poke.api.Pokemon;
import cl.fp.pokedex.domain.poke.api.PokemonAbility;
import cl.fp.pokedex.domain.poke.api.PokemonSpecies;
import cl.fp.pokedex.domain.poke.api.PokemonSprites;
import cl.fp.pokedex.domain.poke.api.PokemonType;
import cl.fp.pokedex.link.builder.PokemonLinkBuilder;
import cl.fp.pokedex.mapper.Mapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.Link;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class DefaultPokemonServiceTest {
    @Mock
    private PokeApiClient pokeApiClient;
    @Mock
    private PokemonLinkBuilder pokemonLinkBuilder;
    @Mock
    private Mapper<List<PokemonType>, List<String>> typesMapper;
    @Mock
    private Mapper<List<PokemonAbility>, List<String>> abilitiesMapper;
    @Mock
    private Mapper<List<FlavorTextEntry>, String> descriptionMapper;
    @Mock
    private Mapper<EvolutionChain, List<String>> evolutionsMapper;
    @Mock
    private Pokemon pokemon;
    @Mock
    private NamedApiResource namedApiResource;
    @Mock
    private PokemonSpecies pokemonSpecies;
    @Mock
    private ApiResource apiResource;
    @Mock
    private EvolutionChain evolutionChain;
    @Mock
    private PokemonSprites pokemonSprites;
    @Mock
    private PokemonType pokemonType;
    @Mock
    private PokemonAbility pokemonAbility;
    @Mock
    private FlavorTextEntry flavorTextEntry;
    @Mock
    private Link link;
    private DefaultPokemonService defaultPokemonService;

    @BeforeEach
    void setUp() {
        defaultPokemonService = new DefaultPokemonService(pokeApiClient, pokemonLinkBuilder, typesMapper,
                abilitiesMapper, descriptionMapper, evolutionsMapper);
    }

    @Test
    void shouldGetDetailPokemon() {
        final String pokemonUrl = "pokemonUrl";
        given(pokeApiClient.getResource(pokemonUrl, Pokemon.class)).willReturn(pokemon);
        given(pokemon.getSpecies()).willReturn(namedApiResource);
        final String pokemonSpeciesUrl = "pokemonSpeciesUrl";
        given(namedApiResource.getUrl()).willReturn(pokemonSpeciesUrl);
        given(pokeApiClient.getResource(pokemonSpeciesUrl, PokemonSpecies.class)).willReturn(pokemonSpecies);
        given(pokemonSpecies.getEvolutionChain()).willReturn(apiResource);
        final String evolutionChainUrl = "evolutionChainUrl";
        given(apiResource.getUrl()).willReturn(evolutionChainUrl);
        given(pokeApiClient.getResource(evolutionChainUrl, EvolutionChain.class)).willReturn(evolutionChain);
        final String name = "name";
        given(pokemon.getName()).willReturn(name);
        given(pokemon.getSprites()).willReturn(pokemonSprites);
        final String imageUrl = "imageUrl";
        given(pokemonSprites.getFrontDefault()).willReturn(imageUrl);
        final List<PokemonType> pokemonTypes = singletonList(pokemonType);
        given(pokemon.getTypes()).willReturn(pokemonTypes);
        final List<String> types = singletonList("type1");
        given(typesMapper.map(pokemonTypes)).willReturn(types);
        final Integer weight = 123;
        given(pokemon.getWeight()).willReturn(weight);
        final List<PokemonAbility> pokemonAbilities = asList(pokemonAbility, pokemonAbility);
        given(pokemon.getAbilities()).willReturn(pokemonAbilities);
        final List<String> abilities = asList("ability1", "ability2");
        given(abilitiesMapper.map(pokemonAbilities)).willReturn(abilities);
        final List<FlavorTextEntry> flavorTextEntries = asList(flavorTextEntry, flavorTextEntry, flavorTextEntry);
        given(pokemonSpecies.getFlavorTextEntries()).willReturn(flavorTextEntries);
        final String description = "description";
        given(descriptionMapper.map(flavorTextEntries)).willReturn(description);
        final List<String> evolutions = asList("evolution1", "evolution2", "evolution3");
        given(evolutionsMapper.map(evolutionChain)).willReturn(evolutions);
        final Integer id = 12;
        given(pokemon.getId()).willReturn(id);
        given(pokemonLinkBuilder.getSelfLink(id)).willReturn(link);

        cl.fp.pokedex.domain.pokedex.Pokemon result = defaultPokemonService.getDetailPokemon(pokemonUrl);

        assertNotNull(result);
        assertEquals(name, result.getName());
        assertEquals(imageUrl, result.getImageUrl());
        assertIterableEquals(types, result.getTypes());
        assertEquals(weight, result.getWeight());
        assertIterableEquals(abilities, result.getAbilities());
        assertEquals(description, result.getDescription());
        assertIterableEquals(evolutions, result.getEvolutions());
        assertIterableEquals(singletonList(link), result.getLinks());
    }

    @AfterEach
    void tearDown() {
        then(pokeApiClient).shouldHaveNoMoreInteractions();
        then(pokemonLinkBuilder).shouldHaveNoMoreInteractions();
        then(typesMapper).shouldHaveNoMoreInteractions();
        then(abilitiesMapper).shouldHaveNoMoreInteractions();
        then(descriptionMapper).shouldHaveNoMoreInteractions();
        then(evolutionsMapper).shouldHaveNoMoreInteractions();
        then(pokemon).shouldHaveNoMoreInteractions();
        then(namedApiResource).shouldHaveNoMoreInteractions();
        then(pokemonSpecies).shouldHaveNoMoreInteractions();
        then(apiResource).shouldHaveNoMoreInteractions();
        then(evolutionChain).shouldHaveNoMoreInteractions();
        then(pokemonSprites).shouldHaveNoMoreInteractions();
        then(pokemonType).shouldHaveNoMoreInteractions();
        then(pokemonAbility).shouldHaveNoMoreInteractions();
        then(flavorTextEntry).shouldHaveNoMoreInteractions();
        then(link).shouldHaveNoMoreInteractions();
    }
}
