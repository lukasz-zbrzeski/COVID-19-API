package com.example.covid19api.parsers.strategy;

public interface ParserStrategy {
    String parseData(String country, String region, String city, String date);

    StrategyName getStrategyName();
}