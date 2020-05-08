package com.example.covid19api.parsers;

import com.example.covid19api.model.History;
import com.example.covid19api.service.DataService;
import com.google.gson.GsonBuilder;
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
        return new GsonBuilder().setPrettyPrinting().create().toJson(getModel(date));
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
                totalConfirmed += Integer.parseInt(strings.get("Confirmed"));
                totalDeaths += Integer.parseInt(strings.get("Deaths"));
                totalRecovered += Integer.parseInt(strings.get("Recovered"));
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
