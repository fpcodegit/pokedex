package cl.fp.pokedex.mapper;

import cl.fp.pokedex.domain.poke.api.ChainLink;
import cl.fp.pokedex.domain.poke.api.EvolutionChain;
import cl.fp.pokedex.domain.poke.api.NamedApiResource;
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
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class EvolutionsMapperTest {
    @Mock
    private EvolutionChain evolutionChain;
    @Mock
    private ChainLink chainLink;
    @Mock
    private NamedApiResource namedApiResource;
    @InjectMocks
    private EvolutionsMapper evolutionsMapper;

    @Test
    void shouldReturnAListWithOneElementWhenPokemonDontHaveEvolutions() {
        given(evolutionChain.getChain()).willReturn(chainLink);
        given(chainLink.getSpecies()).willReturn(namedApiResource);
        final String name = "name";
        given(namedApiResource.getName()).willReturn(name);
        given(chainLink.getEvolvesTo()).willReturn(emptyList());

        List<String> result = evolutionsMapper.map(evolutionChain);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(name, result.get(0));
    }

    @Test
    void shouldReturnAListWithMoreThanOneElementWhenPokemonHaveEvolutions() {
        given(evolutionChain.getChain()).willReturn(chainLink);
        given(chainLink.getSpecies()).willReturn(namedApiResource);
        final String name1 = "name1";
        final String name2 = "name2";
        final String name3 = "name3";
        given(namedApiResource.getName()).willReturn(name1, name2, name3);
        given(chainLink.getEvolvesTo()).willReturn(singletonList(chainLink), singletonList(chainLink), emptyList());

        List<String> result = evolutionsMapper.map(evolutionChain);

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(name1, result.get(0));
        assertEquals(name2, result.get(1));
        assertEquals(name3, result.get(2));
    }

    @AfterEach
    void tearDown() {
        then(evolutionChain).shouldHaveNoMoreInteractions();
        then(chainLink).shouldHaveNoMoreInteractions();
        then(namedApiResource).shouldHaveNoMoreInteractions();
    }
}
