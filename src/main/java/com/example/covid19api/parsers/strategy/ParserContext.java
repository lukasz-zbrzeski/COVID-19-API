package com.example.covid19api.parsers.strategy;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class ParserContext {
    private Map<StrategyName, ParserStrategy> strategies;

    public ParserContext(Set<ParserStrategy> strategySet) {
        createStrategy(strategySet);
    }

    public ParserStrategy findStrategy(StrategyName strategyName) {
        return strategies.get(strategyName);
    }

    public String getData(String country, String region, String city, String date, ParserStrategy strategy) {
        return strategy.parseData(country, region, city, date);
    }

    private void createStrategy(Set<ParserStrategy> strategySet) {
        strategies = new HashMap<>();
        strategySet.forEach(strategy -> strategies.put(strategy.getStrategyName(), strategy));
    }
}
