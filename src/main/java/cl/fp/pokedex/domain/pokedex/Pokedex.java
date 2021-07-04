package cl.fp.pokedex.domain.pokedex;

import lombok.Data;

import java.util.List;

@Data
public class Pokedex {
    private List<Pokemon> pokemons;
}
