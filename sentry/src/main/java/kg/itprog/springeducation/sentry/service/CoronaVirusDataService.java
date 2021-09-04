package kg.itprog.springeducation.sentry.service;

import kg.itprog.springeducation.db.entity.CoronaStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
    private static final Logger LOGGER = LoggerFactory.getLogger(CoronaVirusDataService.class);

    private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    public List<CoronaStats> fetchVirusData() {
        try {
            List<CoronaStats> newStats = new ArrayList<>();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(VIRUS_DATA_URL))
                    .build();
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

            StringReader csvBodyReader = new StringReader(httpResponse.body());
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
            for (CSVRecord record : records) {
                CoronaStats locationStat = new CoronaStats();
                locationStat.setState(record.get("Province/State"));
                locationStat.setCountry(record.get("Country/Region"));
                int latestCases = Integer.parseInt(record.get(record.size() - 1));
                int previousDayCases = Integer.parseInt(record.get(record.size() - 2));
                locationStat.setLatestTotalCases(latestCases);
                locationStat.setDiffFromPreviousDay(latestCases - previousDayCases);
                newStats.add(locationStat);
            }
            return newStats;
        } catch (IOException | InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

}
