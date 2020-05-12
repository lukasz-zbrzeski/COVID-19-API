package com.example.covid19api.controller;

import com.example.covid19api.parsers.strategy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DataController {
    private final ParserContext context;

    private final HttpHeaders httpHeaders = new HttpHeaders();

    @Autowired
    public DataController(ParserContext context) {
        this.context = context;
    }

    @RequestMapping(value = "/actual", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> searchActualData() {
        this.httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");

        return new ResponseEntity<>(
                context.getData(null, null, null, null, StrategyName.ACTUAL_PARSER_STRATEGY),
                this.httpHeaders,
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "/actual/location", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> searchActualDataByLocation(
            @RequestParam(defaultValue = "") String country,
            @RequestParam(defaultValue = "") String region,
            @RequestParam(defaultValue = "") String city
    ) {
        this.httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");

        return new ResponseEntity<>(
                context.getData(country, region, city, null, StrategyName.LOCATION_PARSER_STRATEGY),
                this.httpHeaders,
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "/history/{date}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> searchHistoricalData(@PathVariable String date) {
        this.httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");

        return new ResponseEntity<>(
                context.getData(null, null, null, date, StrategyName.HISTORY_PARSER_STRATEGY),
                this.httpHeaders,
                HttpStatus.OK
        );
    }
}
