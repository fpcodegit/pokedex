package cl.fp.pokedex.domain.pokedex;

import lombok.Data;

import java.util.List;

@Data
public class Pokemon {
    private String imageUrl;
    private List<String> type;
    private Integer weight;
    private List<String> abilities;
    private String description;
    private List<String> evolutions;
}
