package cl.fp.pokedex.client;

public interface PokeApiClient {
    <T> T getResource(String url, Class<T> resourceType);
}
