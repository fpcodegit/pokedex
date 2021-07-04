package cl.fp.pokedex.domain.poke.api;

import lombok.Data;

import java.util.List;

@Data
public class Pokemon {
    private Integer weight;
    private List<Type> types;
    private List<Ability> abilities;
    private NamedApiResource species;
    private Sprites sprites;
}
