package com.example.covid19api.controller;

import com.example.covid19api.parsers.strategy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DataController {
    private final ParserContext context;

    @Autowired
    public DataController(ParserContext context) {
        this.context = context;
    }

    @RequestMapping(value = "/actual", method = RequestMethod.GET)
    @ResponseBody
    public String searchActualData() {
        return context.getData(null, null, null, null, context.findStrategy(StrategyName.ACTUAL_PARSER_STRATEGY));
    }

    @RequestMapping(value = "/actual/location", method = RequestMethod.GET)
    @ResponseBody
    public String searchActualDataByLocation(
            @RequestParam(defaultValue = "") String country,
            @RequestParam(defaultValue = "") String region,
            @RequestParam(defaultValue = "") String city
    ) {
        return context.getData(country, region, city, null, context.findStrategy(StrategyName.LOCATION_PARSER_STRATEGY));
    }

    @RequestMapping(value = "/history/{date}", method = RequestMethod.GET)
    @ResponseBody
    public String searchHistoryData(@PathVariable String date) {
        return context.getData(null, null, null, date, context.findStrategy(StrategyName.HISTORY_PARSER_STRATEGY));
    }
}
