package com.example.covid19api.data;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Data {
    private static final String DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/" + getYesterdayDate() + ".csv";

    public static String getYesterdayDate() {
        Date date = DateUtils.addDays(new Date(), -1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        return dateFormat.format(date);
    }

    public String getData() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(DATA_URL, String.class);
    }
}
