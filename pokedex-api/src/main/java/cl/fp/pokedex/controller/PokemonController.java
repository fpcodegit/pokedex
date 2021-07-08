package cl.fp.pokedex.controller;

import cl.fp.pokedex.domain.pokedex.Pokemon;
import cl.fp.pokedex.service.PokemonService;
import cl.fp.pokedex.url.builder.PokeApiUrlBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("pokemon")
@RequiredArgsConstructor
@RestController
public class PokemonController {
    private final PokemonService defaultPokemonService;
    private final PokeApiUrlBuilder pokeApiUrlBuilder;

    @CrossOrigin("${pokedex.api.cors.enabled}")
    @GetMapping("{id}")
    public Pokemon getPokemon(@PathVariable String id) {
        final String pokemonUrl = pokeApiUrlBuilder.getPokemonWithId(id);
        return defaultPokemonService.getDetailPokemon(pokemonUrl);
    }
}
