package cl.fp.pokedex.domain.pokedex;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.Link;

import java.util.List;

@Builder(toBuilder = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pokemon {
    private String name;
    private String imageUrl;
    private List<String> types;
    private Integer weight;
    private List<String> abilities;
    private String description;
    private List<String> evolutions;
    private List<Link> links;
}
