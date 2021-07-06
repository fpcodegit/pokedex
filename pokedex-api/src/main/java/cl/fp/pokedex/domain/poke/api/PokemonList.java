package cl.fp.pokedex.domain.poke.api;

import lombok.Data;

import java.util.List;

@Data
public class PokemonList {
    private List<NamedApiResource> results;
}
