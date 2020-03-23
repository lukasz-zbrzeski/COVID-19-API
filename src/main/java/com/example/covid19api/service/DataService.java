package com.example.covid19api.service;

import com.example.covid19api.parsers.ActualParser;
import com.example.covid19api.parsers.HistoryParser;
import com.example.covid19api.parsers.LocationParser;
import org.springframework.stereotype.Service;

@Service
public class DataService {
    private ActualParser actualParser = new ActualParser();
    private LocationParser locationParser = new LocationParser();
    private HistoryParser historyParser = new HistoryParser();

    public String getActualData() {
        return actualParser.parseData();
    }

    public String searchDataByCountry(String country) {
        return locationParser.parseData(locationParser.getCountries().indexOf(country));
    }

    public String searchDataByRegion(String region) {
        return locationParser.parseData(locationParser.getRegions().indexOf(region));
    }

    public String searchDataByCountryAndRegion(String country, String region) {
        int index = 0;
        for (int i = 0; i < locationParser.getCountries().size(); i++) {
            if (
                    (country.equals(locationParser.getCountries().get(i))
                            && (region.equals(locationParser.getRegions().get(i)))
                    )) {
                index = i;
            }
        }
        return locationParser.parseData(index);
    }

    public String getHistoricalData(String date) {
        return historyParser.parseData(date);
    }
}