package com.example.covid19api.parsers;

import com.example.covid19api.model.Location;
import com.example.covid19api.service.DataService;
import com.google.gson.GsonBuilder;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class LocationParser {
    private final DataService dataService;

    @Autowired
    public LocationParser(DataService dataService) {
        this.dataService = dataService;
    }

    // Containers for records
    private final List<String> lastUpdates = new ArrayList<>();
    private final List<String> countries = new ArrayList<>();
    private final List<String> regions = new ArrayList<>();
    private final List<String> cities = new ArrayList<>();
    private final List<String> lats = new ArrayList<>();
    private final List<String> lons = new ArrayList<>();
    private final List<Integer> confirmedCases = new ArrayList<>();
    private final List<Integer> deathCases = new ArrayList<>();
    private final List<Integer> recoveredCases = new ArrayList<>();
    private final List<Integer> activeCases = new ArrayList<>();

    public String parseData(String country, String region, String city) {
        getData();
        return new GsonBuilder().setPrettyPrinting().create().toJson(getModel(country, region, city));
    }

    private Location getModel(String country, String region, String city) {
        int index = 0;

        if (!(country.equals("")) && region.equals("") && city.equals("")) {
            index = linearSearch(countries, country);
        } else if (!(country.equals("")) && !(region.equals("")) && city.equals("")) {
            index = linearSearch(regions, region);
        } else if (!(country.equals("")) && !(region.equals("")) && !(city.equals(""))) {
            index = linearSearch(cities, city);
        }

        return new Location(
                lastUpdates.get(index),
                countries.get(index),
                regions.get(index),
                cities.get(index),
                lats.get(index),
                lons.get(index),
                confirmedCases.get(index),
                deathCases.get(index),
                recoveredCases.get(index),
                activeCases.get(index)
        );
    }

    private void getData() {
        StringReader stringReader = new StringReader(dataService.getActualData());

        try {
            CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(stringReader);

            for (CSVRecord strings : parser) {
                lastUpdates.add(strings.get("Last_Update"));
                countries.add(strings.get("Country_Region"));
                regions.add(strings.get("Province_State"));
                cities.add(strings.get("Admin2")); // That's the name of cities header
                lats.add(strings.get("Lat"));
                lons.add(strings.get("Long_"));
                confirmedCases.add(Integer.parseInt(strings.get("Confirmed")));
                deathCases.add(Integer.parseInt(strings.get("Deaths")));
                recoveredCases.add(Integer.parseInt(strings.get("Recovered")));
                activeCases.add(Integer.parseInt(strings.get("Active")));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int linearSearch(List<String> list, String elementToSearch) {

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(elementToSearch))
                return i;
        }
        return -1;
    }
}
