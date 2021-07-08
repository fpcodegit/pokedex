package cl.fp.pokedex.url.builder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class PokeApiUrlBuilder {
    @Value("${pokedex.api.pokemon.base.url}")
    private String apiBaseUrl;

    public String getListWithLimitAndOffset(Integer limit, Integer offset) {
        return UriComponentsBuilder.fromHttpUrl(apiBaseUrl)
                .queryParam("limit", limit)
                .queryParam("offset", offset)
                .build()
                .toString();
    }

    public String getPokemonWithId(String id) {
        return UriComponentsBuilder.fromHttpUrl(apiBaseUrl)
                .path(id)
                .build()
                .toString();
    }
}
