package cl.fp.pokedex.mapper;

import cl.fp.pokedex.domain.poke.api.FlavorTextEntry;
import cl.fp.pokedex.domain.poke.api.NamedApiResource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
import static org.springframework.test.util.ReflectionTestUtils.setField;

@ExtendWith(MockitoExtension.class)
class DescriptionMapperTest {
    private static final String DESCRIPTION_LANGUAGE = "en";
    private static final String DESCRIPTION_NOT_AVAILABLE_MESSAGE = "NotAvailable";
    @Mock
    private FlavorTextEntry flavorTextEntry;
    @Mock
    private NamedApiResource namedApiResource;
    @InjectMocks
    private DescriptionMapper descriptionMapper;

    @BeforeEach
    void setUp() {
        setField(descriptionMapper, "descriptionLanguage", DESCRIPTION_LANGUAGE);
        setField(descriptionMapper, "descriptionNotAvailableMessage", DESCRIPTION_NOT_AVAILABLE_MESSAGE);
    }

    @Test
    void shouldReturnAnEmptyStringWhenSourceIsNull() {
        String result = descriptionMapper.map(null);

        assertNotNull(result);
        assertEquals("", result);
    }

    @Test
    void shouldReturnAnEmptyStringWhenSourceIsEmpty() {
        List<FlavorTextEntry> source = emptyList();

        String result = descriptionMapper.map(source);

        assertNotNull(result);
        assertEquals("", result);
    }

    @Test
    void shouldReturnDescriptionWhenSourceHasDataInTheWantedLanguage() {
        List<FlavorTextEntry> source = singletonList(flavorTextEntry);
        given(flavorTextEntry.getLanguage()).willReturn(namedApiResource);
        given(namedApiResource.getName()).willReturn(DESCRIPTION_LANGUAGE);
        final String flavorText = "flavorText";
        given(flavorTextEntry.getFlavorText()).willReturn(flavorText);

        String result = descriptionMapper.map(source);

        assertNotNull(result);
        assertEquals(flavorText, result);
    }

    @Test
    void shouldReturnNotAvailableStringWhenSourceHasNotDataInTheWantedLanguage() {
        List<FlavorTextEntry> source = singletonList(flavorTextEntry);
        given(flavorTextEntry.getLanguage()).willReturn(namedApiResource);
        given(namedApiResource.getName()).willReturn("other");

        String result = descriptionMapper.map(source);

        assertNotNull(result);
        assertEquals(DESCRIPTION_NOT_AVAILABLE_MESSAGE, result);
    }

    @AfterEach
    void tearDown() {
        then(flavorTextEntry).shouldHaveNoMoreInteractions();
        then(namedApiResource).shouldHaveNoMoreInteractions();
    }
}
