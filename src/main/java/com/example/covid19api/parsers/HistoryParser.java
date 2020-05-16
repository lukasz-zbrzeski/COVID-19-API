package com.example.covid19api.parsers;

import com.example.covid19api.model.History;
import com.example.covid19api.service.DataService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class HistoryParser {
    private final DataService dataService;

    @Autowired
    public HistoryParser(DataService dataService) {
        this.dataService = dataService;
    }

    public String parseData(String date) {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(getModel(date));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private History getModel(String date) {
        return new History(
                date,
                getData(date).get(0), // Total confirmed cases
                getData(date).get(1), // Total deaths cases
                getData(date).get(2), // Total recovered cases
                getData(date).get(0) - (getData(date).get(1) + getData(date).get(2)) // Total active cases
        );
    }

    private List<Integer> getData(String date) {
        StringReader stringReader = new StringReader(dataService.getHistoricalData(date));

        List<Integer> listOfTotalCases = new ArrayList<>();

        int totalConfirmed = 0;
        int totalDeaths = 0;
        int totalRecovered = 0;

        try {
            CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(stringReader);

            for (CSVRecord strings : parser) {
                totalConfirmed += Integer.parseInt(strings.get("Confirmed").replaceFirst("", "0"));
                totalDeaths += Integer.parseInt(strings.get("Deaths").replaceFirst("", "0"));
                totalRecovered += Integer.parseInt(strings.get("Recovered").replaceFirst("", "0"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        listOfTotalCases.add(totalConfirmed);
        listOfTotalCases.add(totalDeaths);
        listOfTotalCases.add(totalRecovered);

        return listOfTotalCases;
    }
}
