package com.example.covid19api.model;

import java.util.Map;
import java.util.TreeMap;

public class Actual {

    private Map<String, Integer> data = new TreeMap<>();

    public Actual() {
    }

    public Actual(int confirmed, int deaths, int recovered) {
        data.put("confirmed", confirmed);
        data.put("deaths", deaths);
        data.put("recovered", recovered);
    }

    public Map<String, Integer> getData() {
        return data;
    }

    public void setData(Map<String, Integer> data) {
        this.data = data;
    }
}
