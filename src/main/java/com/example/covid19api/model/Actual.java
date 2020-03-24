package com.example.covid19api.model;

import java.util.Map;
import java.util.TreeMap;

public class Actual {

    private final Map<String, Integer> data = new TreeMap<>();

    public Actual() {
    }

    public Actual(int confirmed, int deaths, int recovered, int active) {
        data.put("confirmed", confirmed);
        data.put("deaths", deaths);
        data.put("recovered", recovered);
        data.put("active", active);
    }

    public Map<String, Integer> getData() {
        return data;
    }
}
