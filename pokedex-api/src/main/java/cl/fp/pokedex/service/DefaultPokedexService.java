package cl.fp.pokedex.service;

import cl.fp.pokedex.client.PokeApiClient;
import cl.fp.pokedex.domain.poke.api.PokemonList;
import cl.fp.pokedex.domain.pokedex.Pokedex;
import cl.fp.pokedex.domain.pokedex.Pokemon;
import cl.fp.pokedex.link.builder.PokedexLinkBuilder;
import cl.fp.pokedex.url.builder.PokeApiUrlBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

@AllArgsConstructor
@Service
public class DefaultPokedexService implements PokedexService {
    private final PokeApiClient restTemplatePokeApiClient;
    private final PokemonService defaultPokemonService;
    private final PokedexLinkBuilder pokedexLinkBuilder;
    private final PokeApiUrlBuilder pokeApiUrlBuilder;

    @Override
    public Pokedex getPokedex(Integer limit, Integer offset) {
        String pokemonListUrl = pokeApiUrlBuilder.getListWithLimitAndOffset(limit, offset);
        PokemonList apiPokemonList = restTemplatePokeApiClient.getResource(pokemonListUrl, PokemonList.class);
        List<Pokemon> pokemonList = apiPokemonList.getResults()
                .stream()
                .map(namedApiResource -> defaultPokemonService.getBasicPokemon(namedApiResource.getUrl()))
                .collect(Collectors.toList());
        return Pokedex.builder()
                .pokemons(pokemonList)
                .links(asList(pokedexLinkBuilder.getSelfLink(limit, offset),
                        pokedexLinkBuilder.getPrevLink(limit, offset),
                        pokedexLinkBuilder.getNextLink(limit, offset)))
                .build();
    }
}
