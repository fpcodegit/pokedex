package cl.fp.pokedex.mapper;

import cl.fp.pokedex.domain.poke.api.NamedApiResource;
import cl.fp.pokedex.domain.poke.api.PokemonAbility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class AbilitiesMapperTest {
    @Mock
    private PokemonAbility pokemonAbility;
    @Mock
    private NamedApiResource namedApiResource;
    @InjectMocks
    private AbilitiesMapper abilitiesMapper;

    @Test
    void shouldReturnAnEmptyListWhenSourceIsNull() {
        List<String> result = abilitiesMapper.map(null);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnAnEmptyListWhenSourceIsEmpty() {
        List<PokemonAbility> source = emptyList();

        List<String> result = abilitiesMapper.map(source);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnAListWhenSourceHasData() {
        List<PokemonAbility> source = singletonList(pokemonAbility);
        given(pokemonAbility.getAbility()).willReturn(namedApiResource);
        final String name = "name";
        given(namedApiResource.getName()).willReturn(name);

        List<String> result = abilitiesMapper.map(source);

        assertNotNull(result);
        assertEquals(source.size(), result.size());
        assertEquals(name, result.get(0));
    }

    @AfterEach
    void tearDown() {
        then(pokemonAbility).shouldHaveNoMoreInteractions();
        then(namedApiResource).shouldHaveNoMoreInteractions();
    }
}
