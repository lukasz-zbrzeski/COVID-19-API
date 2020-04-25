package com.example.covid19api.parsers.strategy;

public class LocationParserStrategy implements ParserStrategy {
    @Override
    public String parseData(String country, String region, String city, String date) {
        return locationParser.parseData(country, region, city);
    }
}
