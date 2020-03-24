package com.example.covid19api.data;

import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Data {
    private static final String ACTUAL_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/" + getYesterdayDate() + ".csv";

    private static String getYesterdayDate() {
        LocalDate date = LocalDate.now().minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        return formatter.format(date);
    }

    public String getActualData() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(ACTUAL_DATA_URL, String.class);
    }

    public String getHistoricalData(String date) {
        String HISTORICAL_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/" + date + ".csv";

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(HISTORICAL_DATA_URL, String.class);
    }
}
