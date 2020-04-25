package com.example.covid19api.parsers.strategy;

public class ParserContext {
    public String getData(String country, String region, String city, String date, ParserStrategy strategy) {
        return strategy.parseData(country, region, city, date);
    }
}
