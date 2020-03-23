package com.example.covid19api.model;

import java.util.Map;
import java.util.TreeMap;

public class Actual {

    private final Map<String, Integer> data = new TreeMap<>();

    public Actual() {
    }

    public Actual(int confirmed, int deaths, int recovered, int existing) {
        data.put("confirmed", confirmed);
        data.put("deaths", deaths);
        data.put("recovered", recovered);
        data.put("existing", existing);
    }

    public Map<String, Integer> getData() {
        return data;
    }
}
