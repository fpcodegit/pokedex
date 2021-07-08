package cl.fp.pokedex.service;

import cl.fp.pokedex.client.PokeApiClient;
import cl.fp.pokedex.domain.poke.api.EvolutionChain;
import cl.fp.pokedex.domain.poke.api.FlavorTextEntry;
import cl.fp.pokedex.domain.poke.api.Pokemon;
import cl.fp.pokedex.domain.poke.api.PokemonAbility;
import cl.fp.pokedex.domain.poke.api.PokemonSpecies;
import cl.fp.pokedex.domain.poke.api.PokemonType;
import cl.fp.pokedex.link.builder.PokemonLinkBuilder;
import cl.fp.pokedex.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.singletonList;

@RequiredArgsConstructor
@Service
public class DefaultPokemonService implements PokemonService {
    private final PokeApiClient restTemplatePokeApiClient;
    private final PokemonLinkBuilder pokemonLinkBuilder;
    private final Mapper<List<PokemonType>, List<String>> typesMapper;
    private final Mapper<List<PokemonAbility>, List<String>> abilitiesMapper;
    private final Mapper<List<FlavorTextEntry>, String> descriptionMapper;
    private final Mapper<EvolutionChain, List<String>> evolutionsMapper;

    @Override
    public cl.fp.pokedex.domain.pokedex.Pokemon getDetailPokemon(String url) {
        Pokemon apiPokemon = restTemplatePokeApiClient.getResource(url, Pokemon.class);
        String pokemonSpeciesUrl = apiPokemon.getSpecies().getUrl();
        PokemonSpecies apiPokemonSpecies = restTemplatePokeApiClient
                .getResource(pokemonSpeciesUrl, PokemonSpecies.class);
        String evolutionChainUrl = apiPokemonSpecies.getEvolutionChain().getUrl();
        EvolutionChain apiEvolutionChain = restTemplatePokeApiClient
                .getResource(evolutionChainUrl, EvolutionChain.class);
        return cl.fp.pokedex.domain.pokedex.Pokemon.builder()
                .name(apiPokemon.getName())
                .imageUrl(apiPokemon.getSprites().getFrontDefault())
                .types(typesMapper.map(apiPokemon.getTypes()))
                .weight(apiPokemon.getWeight())
                .abilities(abilitiesMapper.map(apiPokemon.getAbilities()))
                .description(descriptionMapper.map(apiPokemonSpecies.getFlavorTextEntries()))
                .evolutions(evolutionsMapper.map(apiEvolutionChain))
                .links(singletonList(pokemonLinkBuilder.getSelfLink(apiPokemon.getId())))
                .build();
    }
}
