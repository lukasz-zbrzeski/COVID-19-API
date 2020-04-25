package com.example.covid19api.parsers.strategy;

public class ActualParserStrategy implements ParserStrategy {
    @Override
    public String parseData(String country, String region, String city, String date) {
        return actualParser.parseData();
    }
}
