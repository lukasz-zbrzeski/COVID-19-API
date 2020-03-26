package com.example.covid19api.parsers;

import com.example.covid19api.data.Data;
import com.example.covid19api.model.Location;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class LocationParser {
    private final Data data = new Data();

    // Containers for records
    private List<String> lastUpdates = new ArrayList<>();
    private List<String> countries = new ArrayList<>();
    private List<String> regions = new ArrayList<>();
    private List<String> cities = new ArrayList<>();
    private List<Double> lats = new ArrayList<>();
    private List<Double> lons = new ArrayList<>();
    private List<Integer> confirmedCases = new ArrayList<>();
    private List<Integer> deathCases = new ArrayList<>();
    private List<Integer> recoveredCases = new ArrayList<>();
    private List<Integer> activeCases = new ArrayList<>();

    public String parseData(int index) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        StringReader stringReader = new StringReader(data.getActualData());

        try {
            CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(stringReader);

            for (CSVRecord strings : parser) {
                lastUpdates.add(strings.get("Last_Update"));
                countries.add(strings.get("Country_Region"));
                regions.add(strings.get("Province_State"));
                cities.add(strings.get("Admin2")); // That's the name of cities header
                lats.add(Double.parseDouble(strings.get("Lat")));
                lons.add(Double.parseDouble(strings.get("Long_")));
                confirmedCases.add(Integer.parseInt(strings.get("Confirmed")));
                deathCases.add(Integer.parseInt(strings.get("Deaths")));
                recoveredCases.add(Integer.parseInt(strings.get("Recovered")));
                activeCases.add(Integer.parseInt(strings.get("Active")));
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        Location model = new Location(
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
        return gson.toJson(model);
    }

    public List<String> getLastUpdates() {
        return lastUpdates;
    }

    public List<String> getCountries() {
        return countries;
    }

    public List<String> getRegions() {
        return regions;
    }

    public List<String> getCities() {
        return cities;
    }

    public List<Double> getLats() {
        return lats;
    }

    public List<Double> getLons() {
        return lons;
    }

    public List<Integer> getConfirmedCases() {
        return confirmedCases;
    }

    public List<Integer> getDeathCases() {
        return deathCases;
    }

    public List<Integer> getRecoveredCases() {
        return recoveredCases;
    }
}
