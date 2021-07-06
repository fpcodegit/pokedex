package cl.fp.pokedex.domain.poke.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FlavorTextEntry {
    @JsonProperty("flavor_text")
    private String flavorText;
    private NamedApiResource language;
}
