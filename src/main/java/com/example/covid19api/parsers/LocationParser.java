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
    private List<String> lats = new ArrayList<>();
    private List<String> lons = new ArrayList<>();
    private List<Integer> confirmedCases = new ArrayList<>();
    private List<Integer> deathCases = new ArrayList<>();
    private List<Integer> recoveredCases = new ArrayList<>();
    private List<Integer> activeCases = new ArrayList<>();

    public String parseData(String country, String region, String city) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        StringReader stringReader = new StringReader(data.getActualData());

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

        int indexOfCountry = linearSearch(countries, country);
        int indexOfRegion = linearSearch(regions, region);
        int indexOfCity = linearSearch(cities, city);

        Location model = null;

        if (!(country.equals("")) && region.equals("") && city.equals("")) {
            model = new Location(
                    lastUpdates.get(indexOfCountry),
                    countries.get(indexOfCountry),
                    regions.get(indexOfCountry),
                    cities.get(indexOfCountry),
                    lats.get(indexOfCountry),
                    lons.get(indexOfCountry),
                    confirmedCases.get(indexOfCountry),
                    deathCases.get(indexOfCountry),
                    recoveredCases.get(indexOfCountry),
                    activeCases.get(indexOfCountry)
            );
        } else if (!(country.equals("")) && !(region.equals("")) && city.equals("")) {
            model = new Location(
                    lastUpdates.get(indexOfRegion),
                    countries.get(indexOfRegion),
                    regions.get(indexOfRegion),
                    cities.get(indexOfRegion),
                    lats.get(indexOfRegion),
                    lons.get(indexOfRegion),
                    confirmedCases.get(indexOfRegion),
                    deathCases.get(indexOfRegion),
                    recoveredCases.get(indexOfRegion),
                    activeCases.get(indexOfRegion)
            );
        } else if (!(country.equals("")) && !(region.equals("")) && !(city.equals(""))) {
            model = new Location(
                    lastUpdates.get(indexOfCity),
                    countries.get(indexOfCity),
                    regions.get(indexOfCity),
                    cities.get(indexOfCity),
                    lats.get(indexOfCity),
                    lons.get(indexOfCity),
                    confirmedCases.get(indexOfCity),
                    deathCases.get(indexOfCity),
                    recoveredCases.get(indexOfCity),
                    activeCases.get(indexOfCity)
            );
        }
        return gson.toJson(model);
    }

    private int linearSearch(List<String> list, String elementToSearch) {

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(elementToSearch))
                return i;
        }
        return -1;
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

    public List<String> getLats() {
        return lats;
    }

    public List<String> getLons() {
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
