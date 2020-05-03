package com.example.covid19api.controller;

import com.example.covid19api.parsers.strategy.ActualParserStrategy;
import com.example.covid19api.parsers.strategy.HistoryParserStrategy;
import com.example.covid19api.parsers.strategy.LocationParserStrategy;
import com.example.covid19api.parsers.strategy.ParserContext;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DataController {
    private final ParserContext context = new ParserContext();

    @RequestMapping(value = "/actual", method = RequestMethod.GET)
    @ResponseBody
    public String searchActualData() {
        return context.getData(null, null, null, null, new ActualParserStrategy());
    }

    @RequestMapping(value = "/actual/location", method = RequestMethod.GET)
    @ResponseBody
    public String searchActualDataByLocation(
            @RequestParam(defaultValue = "") String country,
            @RequestParam(defaultValue = "") String region,
            @RequestParam(defaultValue = "") String city
    ) {
        return context.getData(country, region, city, null, new LocationParserStrategy());
    }

    @RequestMapping(value = "/history/{date}", method = RequestMethod.GET)
    @ResponseBody
    public String searchHistoryData(@PathVariable String date) {
        return context.getData(null, null, null, date, new HistoryParserStrategy());
    }
}
