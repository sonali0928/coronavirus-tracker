package com.demo.coronavirustracker.service;

import com.demo.coronavirustracker.model.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


@Service
public class CoronaVirusDataService {
private static String VIRUS_URI ="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/01-01-2021.csv";
private List<LocationStats> allStats = new ArrayList<>();


@PostConstruct
@Scheduled(cron = "* * 1 * * *")
public void fetchVirusData() throws IOException, InterruptedException {
    List<LocationStats> newStats = new ArrayList<>();
    HttpClient httpClient = HttpClient.newHttpClient();
    HttpRequest httpRequest =  HttpRequest.newBuilder().uri(URI.create(VIRUS_URI)).build();
    HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

    StringReader csvReader = new StringReader(httpResponse.body());

    Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(csvReader);
    for (CSVRecord record : records) {
        LocationStats locationStats = new LocationStats();
        locationStats.setState(record.get("Province_State"));
        locationStats.setCountry(record.get("Country_Region"));
        locationStats.setLatestTotalCase(record.get(record.size()-1));
        newStats.add(locationStats);
    }
    this.allStats = newStats;
    }

    public List<LocationStats> getAllStats() {
        return allStats;
    }

    public void setAllStats(List<LocationStats> allStats) {
        this.allStats = allStats;
    }
}
