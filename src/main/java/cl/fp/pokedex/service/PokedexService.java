package cl.fp.pokedex.service;

import cl.fp.pokedex.domain.pokedex.Pokedex;

public interface PokedexService {
    Pokedex getPokedex(Integer limit, Integer offset);
}
