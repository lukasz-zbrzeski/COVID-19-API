package com.example.covid19api.parsers.strategy;

import com.example.covid19api.parsers.ActualParser;
import com.example.covid19api.parsers.HistoryParser;
import com.example.covid19api.parsers.LocationParser;

public interface ParserStrategy {
    ActualParser actualParser = new ActualParser();
    LocationParser locationParser = new LocationParser();
    HistoryParser historyParser = new HistoryParser();

    String parseData(String country, String region, String city, String date);
}