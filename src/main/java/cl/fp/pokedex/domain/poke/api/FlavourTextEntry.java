package cl.fp.pokedex.domain.poke.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FlavourTextEntry {
    @JsonProperty("flavour_text")
    private String flavourText;
    private NamedApiResource language;
}
