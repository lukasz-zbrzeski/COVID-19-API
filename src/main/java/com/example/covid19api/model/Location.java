package com.example.covid19api.model;

import java.util.Map;
import java.util.TreeMap;

public class Location {

    private String last_update;

    private Map<String, String> location = new TreeMap<>();

    private Map<String, Double> coordinates = new TreeMap<>();

    private Map<String, Integer> data = new TreeMap<>();

    public Location() {
    }

    public Location(String last_update, String country, String region, double lat, double lon, int confirmed, int deaths, int recovered) {
        this.last_update = last_update;

        location.put("country", country);
        location.put("region", region);

        coordinates.put("lat", lat);
        coordinates.put("lon", lon);

        data.put("confirmed", confirmed);
        data.put("deaths", deaths);
        data.put("recovered", recovered);
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    public Map<String, String> getLocation() {
        return location;
    }

    public void setLocation(Map<String, String> location) {
        this.location = location;
    }

    public Map<String, Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Map<String, Double> coordinates) {
        this.coordinates = coordinates;
    }

    public Map<String, Integer> getData() {
        return data;
    }

    public void setData(Map<String, Integer> data) {
        this.data = data;
    }
}
