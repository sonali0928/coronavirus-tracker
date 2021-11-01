package com.demo.coronavirustracker.model;

public class LocationStats {
    private String country;
    private String state;
    private String latestTotalCase;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLatestTotalCase() {
        return latestTotalCase;
    }

    public void setLatestTotalCase(String latestTotalCase) {
        this.latestTotalCase = latestTotalCase;
    }
}
