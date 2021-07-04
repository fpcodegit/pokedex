package cl.fp.pokedex.domain.pokedex;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder(toBuilder = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pokemon {
    private Integer id;
    private String name;
    private String imageUrl;
    private List<String> type;
    private Integer weight;
    private List<String> abilities;
    private String description;
    private List<String> evolutions;
}
