package cl.fp.pokedex.mapper;

import cl.fp.pokedex.domain.poke.api.NamedApiResource;
import cl.fp.pokedex.domain.poke.api.PokemonAbility;
import cl.fp.pokedex.domain.poke.api.PokemonType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class TypesMapperTest {
    @Mock
    private PokemonType pokemonType;
    @Mock
    private NamedApiResource namedApiResource;
    @InjectMocks
    private TypesMapper typesMapper;

    @Test
    void shouldReturnAnEmptyListWhenSourceIsNull() {
        List<String> result = typesMapper.map(null);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnAnEmptyListWhenSourceIsEmpty() {
        List<PokemonType> source = emptyList();

        List<String> result = typesMapper.map(source);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnAListWhenSourceHasData() {
        List<PokemonType> source = singletonList(pokemonType);
        given(pokemonType.getType()).willReturn(namedApiResource);
        final String name = "name";
        given(namedApiResource.getName()).willReturn(name);

        List<String> result = typesMapper.map(source);

        assertNotNull(result);
        assertEquals(source.size(), result.size());
        assertEquals(name, result.get(0));
    }

    @AfterEach
    void tearDown() {
        then(pokemonType).shouldHaveNoMoreInteractions();
        then(namedApiResource).shouldHaveNoMoreInteractions();
    }
}
