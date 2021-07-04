package cl.fp.pokedex.controller;

import cl.fp.pokedex.domain.pokedex.Pokedex;
import cl.fp.pokedex.service.PokedexService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping("pokedex")
@RequiredArgsConstructor
@RestController
public class PokedexController {
    private final PokedexService defaultPokedexService;
    @Value("${pokedex.api.default.limit}")
    private Integer defaultLimit;
    @Value("${pokedex.api.default.offset}")
    private Integer defaultOffset;

    @GetMapping
    public Pokedex getPokedex(@RequestParam("limit") Optional<Integer> optionalLimit,
                              @RequestParam("offset") Optional<Integer> optionalOffset) {
        Integer limit = optionalLimit.orElse(defaultLimit);
        Integer offset = optionalOffset.orElse(defaultOffset);
        return defaultPokedexService.getPokedex(limit, offset);
    }
}
