package cl.fp.pokedex.client;

import cl.fp.pokedex.domain.poke.api.PokemonList;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class RestTemplatePokeApiClient implements PokeApiClient {
    private final RestTemplate restTemplate;
    @Value("${pokedex.api.pokemon.list.url}")
    private String pokemonListUrl;

    @Override
    public PokemonList getPokemonList(Integer limit, Integer offset) {
        return restTemplate.getForObject(pokemonListUrl, PokemonList.class, limit, offset);
    }

    @Override
    public <T> T getResource(String url, Class<T> resourceType) {
        return restTemplate.getForObject(url, resourceType);
    }
}
