package cl.fp.pokedex.client;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Cacheable("pokeApi")
@Component
@RequiredArgsConstructor
public class RestTemplatePokeApiClient implements PokeApiClient {
    private final RestTemplate restTemplate;

    @Override
    public <T> T getResource(String url, Class<T> resourceType) {
        return restTemplate.getForObject(url, resourceType);
    }
}
