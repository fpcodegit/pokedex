package cl.fp.pokedex.domain.poke.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Type {
    @JsonProperty("type")
    private NamedApiResource typeNamedResource;
}
