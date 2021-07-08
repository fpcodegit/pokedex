package cl.fp.pokedex.mapper;

import cl.fp.pokedex.domain.poke.api.FlavorTextEntry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DescriptionMapper implements Mapper<List<FlavorTextEntry>, String> {
    @Value("${pokedex.api.pokemon.description.language}")
    private String descriptionLanguage;
    @Value("${pokedex.api.pokemon.description.not.available}")
    private String descriptionNotAvailableMessage;

    @Override
    public String map(List<FlavorTextEntry> source) {
        if (source == null || source.isEmpty()) {
            return "";
        }
        return source.stream()
                .filter(flavorTextEntry -> descriptionLanguage.equals(flavorTextEntry.getLanguage().getName()))
                .findFirst()
                .map(FlavorTextEntry::getFlavorText)
                .orElse(descriptionNotAvailableMessage);
    }
}
