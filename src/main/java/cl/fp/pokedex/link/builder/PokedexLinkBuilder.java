package cl.fp.pokedex.link.builder;

import cl.fp.pokedex.controller.PokedexController;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.Link.of;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class PokedexLinkBuilder {
    public Link getSelfLink(Integer limit, Integer offset) {
        return of(linkTo(PokedexController.class)
                .toUriComponentsBuilder()
                .queryParam("limit", limit)
                .queryParam("offset", offset)
                .build()
                .toString());
    }

    public Link getNextLink(Integer limit, Integer offset) {
        return of(linkTo(PokedexController.class)
                .toUriComponentsBuilder()
                .queryParam("limit", limit)
                .queryParam("offset", offset + limit)
                .build()
                .toString(), "next");
    }
}
