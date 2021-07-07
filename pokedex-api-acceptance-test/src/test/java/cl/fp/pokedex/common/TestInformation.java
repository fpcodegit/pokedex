package cl.fp.pokedex.common;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TestInformation {
    private final Map<String, Object> testInformationHolder;

    public TestInformation() {
        testInformationHolder = new HashMap<>();
    }

    public void addObject(String key, Object object) {
        testInformationHolder.put(key, object);
    }

    public <T> T getObjectOfType(String key, Class<T> type) {
        return (T) testInformationHolder.get(key);
    }
}
