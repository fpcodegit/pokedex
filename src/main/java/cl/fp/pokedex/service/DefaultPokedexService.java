package cl.fp.pokedex.service;

import cl.fp.pokedex.client.PokeApiClient;
import cl.fp.pokedex.domain.poke.api.PokemonList;
import cl.fp.pokedex.domain.pokedex.Pokedex;
import cl.fp.pokedex.domain.pokedex.Pokemon;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DefaultPokedexService implements PokedexService {
    private final PokeApiClient restTemplatePokeApiClient;
    private final PokemonService defaultPokemonService;

    @Override
    public Pokedex getPokedex(Integer limit, Integer offset) {
        PokemonList apiPokemonList = restTemplatePokeApiClient.getPokemonList(limit, offset);
        List<Pokemon> pokemonList = apiPokemonList.getResults()
                .stream()
                .map(namedApiResource -> defaultPokemonService.getBasicPokemon(namedApiResource.getUrl()))
                .collect(Collectors.toList());
        return Pokedex.builder()
                .pokemons(pokemonList)
                .build();
    }
}
