package cl.fp.pokedex.link.builder;

import cl.fp.pokedex.controller.PokemonController;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.Link.of;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class PokemonLinkBuilder {
    public Link getSelfLink(Integer pokemonId) {
        return of(linkTo(PokemonController.class).slash(pokemonId).toString());
    }
}
