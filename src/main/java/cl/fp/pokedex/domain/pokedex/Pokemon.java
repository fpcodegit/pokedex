package cl.fp.pokedex.domain.pokedex;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Pokemon {
    private String name;
    private String imageUrl;
    private List<String> type;
    private Integer weight;
    private List<String> abilities;
    private String description;
    private List<String> evolutions;
}
