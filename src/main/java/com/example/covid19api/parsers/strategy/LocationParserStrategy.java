package com.example.covid19api.parsers.strategy;

import com.example.covid19api.parsers.LocationParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocationParserStrategy implements ParserStrategy {
    private final LocationParser locationParser;

    @Autowired
    public LocationParserStrategy(LocationParser locationParser) {
        this.locationParser = locationParser;
    }

    @Override
    public String parseData(String country, String region, String city, String date) {
        return locationParser.parseData(country, region, city);
    }

    @Override
    public StrategyName getStrategyName() {
        return StrategyName.LOCATION_PARSER_STRATEGY;
    }
}
