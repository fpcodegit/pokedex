package cl.fp.pokedex.mapper;

import cl.fp.pokedex.domain.poke.api.ChainLink;
import cl.fp.pokedex.domain.poke.api.EvolutionChain;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EvolutionsMapper implements Mapper<EvolutionChain, List<String>> {
    @Override
    public List<String> map(EvolutionChain source) {
        List<String> evolutions = new ArrayList<>();
        ChainLink currentChainLink = source.getChain();
        while (currentChainLink != null) {
            evolutions.add(currentChainLink.getSpecies().getName());
            List<ChainLink> evolvesTo = currentChainLink.getEvolvesTo();
            if (evolvesTo.isEmpty()) {
                break;
            }
            currentChainLink = evolvesTo.get(0);
        }
        return evolutions;
    }
}
