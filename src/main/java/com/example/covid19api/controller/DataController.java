package com.example.covid19api.controller;

import com.example.covid19api.parsers.ActualParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DataController {
    ActualParser actualParser = new ActualParser();

    @GetMapping("/actual")
    public String actual() {
        return actualParser.parseData();
    }
}
