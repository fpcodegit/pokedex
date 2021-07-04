package cl.fp.pokedex.domain.poke.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PokemonSpecies {
    @JsonProperty("evolution_chain")
    private ApiResource evolutionChain;
    @JsonProperty("flavour_text_entries")
    private List<FlavourTextEntry> flavourTextEntries;
}
