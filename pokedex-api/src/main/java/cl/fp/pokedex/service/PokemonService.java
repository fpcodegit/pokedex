package cl.fp.pokedex.service;

import cl.fp.pokedex.domain.pokedex.Pokemon;

public interface PokemonService {
    Pokemon getDetailPokemon(String url);
}
