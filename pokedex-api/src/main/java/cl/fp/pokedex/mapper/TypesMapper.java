package cl.fp.pokedex.mapper;

import cl.fp.pokedex.domain.poke.api.NamedApiResource;
import cl.fp.pokedex.domain.poke.api.PokemonType;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TypesMapper implements Mapper<List<PokemonType>, List<String>> {
    @Override
    public List<String> map(List<PokemonType> source) {
        if (source == null || source.isEmpty()) {
            return Collections.emptyList();
        }
        return source.stream()
                .map(PokemonType::getType)
                .map(NamedApiResource::getName)
                .collect(Collectors.toList());
    }
}
