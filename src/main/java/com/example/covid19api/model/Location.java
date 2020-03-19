package com.example.covid19api.model;

public class Location {

    private String last_update;

    private String country;

    private String region;

    private double lat;

    private double lon;

    private int confirmed;

    private int deaths;

    private int recovered;

    public Location() {
    }

    public Location(String last_update, String country, String region, double lat, double lon, int confirmed, int deaths, int recovered) {
        this.last_update = last_update;
        this.country = country;
        this.region = region;
        this.lat = lat;
        this.lon = lon;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }
}
