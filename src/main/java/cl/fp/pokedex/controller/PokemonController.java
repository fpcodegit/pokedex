package cl.fp.pokedex.controller;

import cl.fp.pokedex.domain.pokedex.Pokemon;
import cl.fp.pokedex.service.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("pokemon")
@RequiredArgsConstructor
@RestController
public class PokemonController {
    private final PokemonService defaultPokemonService;
    @Value("${pokedex.api.pokemon.detail.base.url}")
    private String basePokemonUrl;

    @GetMapping("{id}")
    public Pokemon getPokemon(@PathVariable String id) {
        final String pokemonUrl = basePokemonUrl + id;
        return defaultPokemonService.getDetailPokemon(pokemonUrl);
    }
}
