package com.example.covid19api.parsers;

import com.example.covid19api.data.Data;
import com.example.covid19api.model.Actual;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;

@Service
public class ActualParser {
    private Data data = new Data();

    public String parseData() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        int totalConfirmed = 0;
        int totalDeaths = 0;
        int totalRecovered = 0;

        StringReader stringReader = new StringReader(data.getData());

        CSVParser parser;

        try {
            parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(stringReader);

            for (CSVRecord strings : parser) {
                totalConfirmed += Integer.parseInt(strings.get("Confirmed"));
                totalDeaths += Integer.parseInt(strings.get("Deaths"));
                totalRecovered += Integer.parseInt(strings.get("Recovered"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        Actual model = new Actual(
                totalConfirmed,
                totalDeaths,
                totalRecovered
        );
        return gson.toJson(model);
    }
}
