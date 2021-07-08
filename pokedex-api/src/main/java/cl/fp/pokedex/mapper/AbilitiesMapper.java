package cl.fp.pokedex.mapper;

import cl.fp.pokedex.domain.poke.api.NamedApiResource;
import cl.fp.pokedex.domain.poke.api.PokemonAbility;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AbilitiesMapper implements Mapper<List<PokemonAbility>, List<String>> {
    @Override
    public List<String> map(List<PokemonAbility> source) {
        if (source == null || source.isEmpty()) {
            return Collections.emptyList();
        }
        return source.stream()
                .map(PokemonAbility::getAbility)
                .map(NamedApiResource::getName)
                .collect(Collectors.toList());
    }
}
