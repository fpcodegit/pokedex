package cl.fp.pokedex.domain.pokedex;

import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.Link;

import java.util.List;

@Builder
@Data
public class Pokedex {
    private List<Pokemon> pokemons;
    private List<Link> links;
}
