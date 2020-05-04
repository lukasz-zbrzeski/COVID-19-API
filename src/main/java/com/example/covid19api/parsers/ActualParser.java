package com.example.covid19api.parsers;

import com.example.covid19api.data.Data;
import com.example.covid19api.model.Actual;
import com.google.gson.GsonBuilder;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class ActualParser {
    private final Data data = new Data();

    public String parseData() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(getModel());
    }

    private Actual getModel() {
        return new Actual(
                getData().get(0), // Total confirmed cases
                getData().get(1), // Total deaths cases
                getData().get(2), // Total recovered cases
                getData().get(3)  // Total active cases
        );
    }

    private List<Integer> getData() {
        StringReader stringReader = new StringReader(data.getActualData());

        List<Integer> listOfTotalCases = new ArrayList<>();

        int totalConfirmed = 0;
        int totalDeaths = 0;
        int totalRecovered = 0;
        int totalActive = 0;

        try {
            CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(stringReader);

            for (CSVRecord strings : parser) {
                totalConfirmed += Integer.parseInt(strings.get("Confirmed"));
                totalDeaths += Integer.parseInt(strings.get("Deaths"));
                totalRecovered += Integer.parseInt(strings.get("Recovered"));
                totalActive += Integer.parseInt(strings.get("Active"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        listOfTotalCases.add(totalConfirmed);
        listOfTotalCases.add(totalDeaths);
        listOfTotalCases.add(totalRecovered);
        listOfTotalCases.add(totalActive);

        return listOfTotalCases;
    }
}
