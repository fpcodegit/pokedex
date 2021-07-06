package cl.fp.pokedex.service;

import cl.fp.pokedex.client.PokeApiClient;
import cl.fp.pokedex.domain.poke.api.ChainLink;
import cl.fp.pokedex.domain.poke.api.EvolutionChain;
import cl.fp.pokedex.domain.poke.api.FlavorTextEntry;
import cl.fp.pokedex.domain.poke.api.NamedApiResource;
import cl.fp.pokedex.domain.poke.api.Pokemon;
import cl.fp.pokedex.domain.poke.api.PokemonAbility;
import cl.fp.pokedex.domain.poke.api.PokemonSpecies;
import cl.fp.pokedex.domain.poke.api.PokemonType;
import cl.fp.pokedex.link.builder.PokemonLinkBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;

@RequiredArgsConstructor
@Service
public class DefaultPokemonService implements PokemonService {
    private final PokeApiClient restTemplatePokeApiClient;
    private final PokemonLinkBuilder pokemonLinkBuilder;
    @Value("${pokedex.api.pokemon.description.language}")
    private String descriptionLanguage;
    @Value("${pokedex.api.pokemon.description.not.available}")
    private String descriptionNotAvailableMessage;

    @Override
    public cl.fp.pokedex.domain.pokedex.Pokemon getBasicPokemon(String url) {
        Pokemon apiPokemon = restTemplatePokeApiClient.getResource(url, Pokemon.class);
        return cl.fp.pokedex.domain.pokedex.Pokemon.builder()
                .name(apiPokemon.getName())
                .imageUrl(apiPokemon.getSprites().getFrontDefault())
                .types(apiPokemon.getTypes().stream()
                        .map(PokemonType::getType)
                        .map(NamedApiResource::getName)
                        .collect(Collectors.toList()))
                .weight(apiPokemon.getWeight())
                .abilities(apiPokemon.getAbilities().stream()
                        .map(PokemonAbility::getAbility)
                        .map(NamedApiResource::getName)
                        .collect(Collectors.toList()))
                .links(singletonList(pokemonLinkBuilder.getSelfLink(apiPokemon.getId())))
                .build();
    }

    @Override
    public cl.fp.pokedex.domain.pokedex.Pokemon getDetailPokemon(String url) {
        Pokemon apiPokemon = restTemplatePokeApiClient.getResource(url, Pokemon.class);
        String pokemonSpeciesUrl = apiPokemon.getSpecies().getUrl();
        PokemonSpecies apiPokemonSpecies = restTemplatePokeApiClient
                .getResource(pokemonSpeciesUrl, PokemonSpecies.class);
        String evolutionChainUrl = apiPokemonSpecies.getEvolutionChain().getUrl();
        EvolutionChain apiEvolutionChain = restTemplatePokeApiClient
                .getResource(evolutionChainUrl, EvolutionChain.class);
        return getBasicPokemon(url).toBuilder()
                .description(apiPokemonSpecies.getFlavorTextEntries().stream()
                        .filter(flavorTextEntry -> descriptionLanguage.equals(flavorTextEntry.getLanguage().getName()))
                        .findFirst()
                        .map(FlavorTextEntry::getFlavorText)
                        .orElse(descriptionNotAvailableMessage))
                .evolutions(getEvolutions(apiEvolutionChain))
                .build();
    }

    private List<String> getEvolutions(EvolutionChain evolutionChain) {
        List<String> evolutions = new ArrayList<>();
        ChainLink currentChainLink = evolutionChain.getChain();
        while (currentChainLink != null) {
            evolutions.add(currentChainLink.getSpecies().getName());
            List<ChainLink> evolvesTo = currentChainLink.getEvolvesTo();
            if (evolvesTo.isEmpty()) {
                break;
            }
            currentChainLink = evolvesTo.get(0);
        }
        return evolutions;
    }
}
