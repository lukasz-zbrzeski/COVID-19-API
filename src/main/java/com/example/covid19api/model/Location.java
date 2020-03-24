package com.example.covid19api.model;

import java.util.Map;
import java.util.TreeMap;

public class Location {

    private String last_update;

    private final Map<String, String> location = new TreeMap<>();

    private final Map<String, Double> coordinates = new TreeMap<>();

    private final Map<String, Integer> data = new TreeMap<>();

    public Location() {
    }

    public Location(String last_update, String country, String region, double lat, double lon, int confirmed, int deaths, int recovered, int active) {
        this.last_update = last_update;

        location.put("country", country);
        location.put("region", region);

        coordinates.put("lat", lat);
        coordinates.put("lon", lon);

        data.put("confirmed", confirmed);
        data.put("deaths", deaths);
        data.put("recovered", recovered);
        data.put("active", active);
    }

    public String getLast_update() {
        return last_update;
    }

    public Map<String, String> getLocation() {
        return location;
    }

    public Map<String, Double> getCoordinates() {
        return coordinates;
    }

    public Map<String, Integer> getData() {
        return data;
    }
}
