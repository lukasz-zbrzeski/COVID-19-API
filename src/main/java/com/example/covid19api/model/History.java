package com.example.covid19api.model;

import java.util.Map;
import java.util.TreeMap;

public class History {

    private String date;

    private final Map<String, Integer> data = new TreeMap<>();

    public History() {
    }

    public History(String date, int confirmed, int deaths, int recovered, int active) {
        this.date = date;

        data.put("confirmed", confirmed);
        data.put("deaths", deaths);
        data.put("recovered", recovered);
        data.put("active", active);
    }

    public String getDate() {
        return date;
    }

    public Map<String, Integer> getData() {
        return data;
    }
}
