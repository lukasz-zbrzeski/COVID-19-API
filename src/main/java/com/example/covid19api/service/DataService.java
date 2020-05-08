package com.example.covid19api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class DataService {
    private final String DATA_URL;
    private static final String FILE_FORMAT = ".csv";

    public DataService(@Value("${data.url}") String DATA_URL) {
        this.DATA_URL = DATA_URL;
    }

    public String getActualData() {
        return new RestTemplate().getForObject(DATA_URL + getYesterdayDate() + FILE_FORMAT , String.class);
    }

    public String getHistoricalData(String date) {
        return new RestTemplate().getForObject(DATA_URL + date + FILE_FORMAT, String.class);
    }

    private static String getYesterdayDate() {
        LocalDate date = LocalDate.now().minusDays(1);
        return DateTimeFormatter.ofPattern("MM-dd-yyyy").format(date);
    }
}
