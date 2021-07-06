package cl.fp.pokedex.link.builder;

import cl.fp.pokedex.controller.PokedexController;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.Link.of;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class PokedexLinkBuilder {
    private static final String LIMIT = "limit";
    private static final String OFFSET = "offset";

    public Link getSelfLink(Integer limit, Integer offset) {
        return of(linkTo(PokedexController.class)
                .toUriComponentsBuilder()
                .queryParam(LIMIT, limit)
                .queryParam(OFFSET, offset)
                .build()
                .toString());
    }

    public Link getNextLink(Integer limit, Integer offset) {
        return of(linkTo(PokedexController.class)
                .toUriComponentsBuilder()
                .queryParam(LIMIT, limit)
                .queryParam(OFFSET, offset + limit)
                .build()
                .toString(), "next");
    }

    public Link getPrevLink(Integer limit, Integer offset) {
        int newOffset = offset - limit;
        if (newOffset < 0) {
            newOffset = 0;
        }
        return of(linkTo(PokedexController.class)
                .toUriComponentsBuilder()
                .queryParam(LIMIT, limit)
                .queryParam(OFFSET, newOffset)
                .build()
                .toString(), "prev");
    }
}
