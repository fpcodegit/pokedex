package cl.fp.pokedex.domain.poke.api;

import lombok.Data;

import java.util.List;

@Data
public class Pokemon {
    private Integer id;
    private String name;
    private Integer weight;
    private List<PokemonType> types;
    private List<PokemonAbility> abilities;
    private NamedApiResource species;
    private PokemonSprites sprites;
}
