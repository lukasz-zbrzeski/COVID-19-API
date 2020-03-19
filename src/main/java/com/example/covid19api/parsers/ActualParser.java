package com.example.covid19api.parsers;

import com.example.covid19api.data.Data;
import com.example.covid19api.model.Actual;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class ActualParser {
    private Data data = new Data();

    public String parseData() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        List<Integer> confirmedCases = new ArrayList<>();
        List<Integer> deathsCases = new ArrayList<>();
        List<Integer> recoveredCases = new ArrayList<>();

        int totalConfirmed = 0;
        int totalDeaths = 0;
        int totalRecovered = 0;

        StringReader stringReaderConfirmed = new StringReader(data.getData());
        StringReader stringReaderDeaths = new StringReader(data.getData());
        StringReader stringReaderRecovered = new StringReader(data.getData());

        CSVParser parserConfirmed;
        CSVParser parserDeaths;
        CSVParser parserRecovered;

        try {
            parserConfirmed = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(stringReaderConfirmed);
            parserDeaths = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(stringReaderDeaths);
            parserRecovered = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(stringReaderRecovered);

            for (CSVRecord strings : parserConfirmed) {
                confirmedCases.add(Integer.parseInt(strings.get("Confirmed")));
            }

            for (CSVRecord strings : parserDeaths) {
                deathsCases.add(Integer.parseInt(strings.get("Deaths")));
            }

            for (CSVRecord strings : parserRecovered) {
                recoveredCases.add(Integer.parseInt(strings.get("Recovered")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Integer confirmedCase : confirmedCases) {
            totalConfirmed += confirmedCase;
        }

        for (Integer deathCase : deathsCases) {
            totalDeaths += deathCase;
        }

        for (Integer recoveredCase : recoveredCases) {
            totalRecovered += recoveredCase;
        }

        Actual model = new Actual(
                totalConfirmed,
                totalDeaths,
                totalRecovered
        );
        return gson.toJson(model);
    }
}
