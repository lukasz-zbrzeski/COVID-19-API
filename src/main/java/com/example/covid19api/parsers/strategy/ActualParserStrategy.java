package com.example.covid19api.parsers.strategy;

import com.example.covid19api.parsers.ActualParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActualParserStrategy implements ParserStrategy {
    private final ActualParser actualParser;

    @Autowired
    public ActualParserStrategy(ActualParser actualParser) {
        this.actualParser = actualParser;
    }

    @Override
    public String parseData(String country, String region, String city, String date) {
        return actualParser.parseData();
    }

    @Override
    public StrategyName getStrategyName() {
        return StrategyName.ACTUAL_PARSER_STRATEGY;
    }
}
