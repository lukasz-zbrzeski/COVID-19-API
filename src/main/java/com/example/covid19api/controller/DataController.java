package com.example.covid19api.controller;

import com.example.covid19api.parsers.strategy.ActualParserStrategy;
import com.example.covid19api.parsers.strategy.HistoryParserStrategy;
import com.example.covid19api.parsers.strategy.LocationParserStrategy;
import com.example.covid19api.parsers.strategy.ParserContext;
import com.example.covid19api.service.DataService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DataController {
    private final Gson gson = new Gson();

    private final DataService dataService;

    private final ParserContext context = new ParserContext();

    @Autowired
    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/actual")
    public String actual() {
        return context.getData(null, null, null, null, new ActualParserStrategy());
    }

    @GetMapping("/actual/location")
    public String searchActualDataByLocation(
            @RequestParam(defaultValue = "") String country,
            @RequestParam(defaultValue = "") String region,
            @RequestParam(defaultValue = "") String city
    ) {
        return context.getData(country, region, city, null, new LocationParserStrategy());
    }

    @GetMapping("/history/{date}")
    public String history(@PathVariable String date) {
        return context.getData(null, null, null, date, new HistoryParserStrategy());
    }
}
