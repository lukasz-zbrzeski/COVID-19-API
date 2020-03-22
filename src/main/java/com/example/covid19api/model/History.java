package com.example.covid19api.model;

import java.util.Map;
import java.util.TreeMap;

public class History {

    private String date;

    private Map<String, Integer> data = new TreeMap<>();

    public History() {
    }

    public History(String date, int confirmed, int deaths, int recovered, int existing) {
        this.date = date;

        data.put("confirmed", confirmed);
        data.put("deaths", deaths);
        data.put("recovered", recovered);
        data.put("existing", existing);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, Integer> getData() {
        return data;
    }

    public void setData(Map<String, Integer> data) {
        this.data = data;
    }
}
