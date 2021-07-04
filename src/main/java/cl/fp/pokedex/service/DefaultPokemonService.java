package cl.fp.pokedex.service;

import cl.fp.pokedex.client.PokeApiClient;
import cl.fp.pokedex.domain.poke.api.NamedApiResource;
import cl.fp.pokedex.domain.poke.api.Pokemon;
import cl.fp.pokedex.domain.poke.api.PokemonAbility;
import cl.fp.pokedex.domain.poke.api.PokemonType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DefaultPokemonService implements PokemonService {
    private final PokeApiClient restTemplatePokeApiClient;

    @Override
    public cl.fp.pokedex.domain.pokedex.Pokemon getBasicPokemon(String url) {
        Pokemon apiPokemon = restTemplatePokeApiClient.getResource(url, Pokemon.class);
        return cl.fp.pokedex.domain.pokedex.Pokemon.builder()
                .name(apiPokemon.getName())
                .imageUrl(apiPokemon.getSprites().getFrontDefault())
                .type(apiPokemon.getTypes().stream()
                        .map(PokemonType::getType)
                        .map(NamedApiResource::getName)
                        .collect(Collectors.toList()))
                .weight(apiPokemon.getWeight())
                .abilities(apiPokemon.getAbilities().stream()
                        .map(PokemonAbility::getAbility)
                        .map(NamedApiResource::getName)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public cl.fp.pokedex.domain.pokedex.Pokemon getDetailPokemon(String url) {
        return null;
    }
}
