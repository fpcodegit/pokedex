package cl.fp.pokedex.client;

import cl.fp.pokedex.domain.poke.api.PokemonList;

public interface PokeApiClient {
    PokemonList getPokemonList(Integer limit, Integer offset);

    <T> T getResource(String url, Class<T> resourceType);
}
