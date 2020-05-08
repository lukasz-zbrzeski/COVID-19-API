package com.example.covid19api.parsers.strategy;

import com.example.covid19api.parsers.HistoryParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HistoryParserStrategy implements ParserStrategy {
    private final HistoryParser historyParser;

    @Autowired
    public HistoryParserStrategy(HistoryParser historyParser) {
        this.historyParser = historyParser;
    }

    @Override
    public String parseData(String country, String region, String city, String date) {
        return historyParser.parseData(date);
    }

    @Override
    public StrategyName getStrategyName() {
        return StrategyName.HISTORY_PARSER_STRATEGY;
    }
}
