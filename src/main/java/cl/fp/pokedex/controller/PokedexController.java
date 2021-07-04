package cl.fp.pokedex.controller;

import cl.fp.pokedex.domain.pokedex.Pokedex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("pokedex")
public class PokedexController {
    @Value("${pokedex.api.default.limit}")
    private Integer defaultLimit;
    @Value("${pokedex.api.default.offset}")
    private Integer defaultOffset;

    @GetMapping
    public Pokedex getPokedex(Optional<Integer> optionalLimit, Optional<Integer> optionalOffset) {
        Integer limit = optionalLimit.orElse(defaultLimit);
        Integer offset = optionalOffset.orElse(defaultOffset);
        return null;
    }
}
