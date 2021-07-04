package cl.fp.pokedex.service;

import cl.fp.pokedex.domain.pokedex.Pokemon;
import org.springframework.stereotype.Service;

@Service
public class DefaultPokemonService implements PokemonService {
    @Override
    public Pokemon getBasicPokemon(String url) {
        return null;
    }

    @Override
    public Pokemon getDetailPokemon(String url) {
        return null;
    }
}
