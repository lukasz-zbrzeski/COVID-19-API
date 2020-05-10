package com.example.covid19api.parsers.strategy;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class ParserContext {
    private final Map<StrategyName, ParserStrategy> strategies;

    public ParserContext(Set<ParserStrategy> strategySet) {
        strategies = new HashMap<>();
        strategySet.forEach(strategy -> strategies.put(strategy.getStrategyName(), strategy));
    }

    public String getData(String country, String region, String city, String date, StrategyName strategyName) {
        return strategies.get(strategyName).parseData(country, region, city, date);
    }
}
