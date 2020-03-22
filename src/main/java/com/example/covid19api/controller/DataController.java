package com.example.covid19api.controller;

import com.example.covid19api.service.DataService;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class DataController {
    private Gson gson = new Gson();

    private DataService dataService = new DataService();

    @GetMapping("/actual")
    public String actual() {
        return dataService.getActualData();
    }

    @GetMapping("/location")
    public String searchDataByLocation(@RequestParam(required = false) String country, @RequestParam(required = false) String region) {
        if (country != null && region != null) {
            return this.dataService.searchDataByCountryAndRegion(country, region);
        } else if (country != null) {
            return this.dataService.searchDataByCountry(country);
        } else if (region != null) {
            return this.dataService.searchDataByRegion(region);
        } else {
            HashMap<String, String> errorMessage = new HashMap<>();
            errorMessage.put("message", "Data not found!");
            return gson.toJson(errorMessage);
        }
    }

    @GetMapping("/history")
    public String history(@RequestParam String date) {
        return dataService.getHistoricalData(date);
    }
}
