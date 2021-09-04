package kg.itprog.springeducation.sentry.scheduler;

import kg.itprog.springeducation.db.entity.CoronaStats;
import kg.itprog.springeducation.sentry.service.CoronaStatsService;
import kg.itprog.springeducation.sentry.service.CoronaVirusDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CoronaStatsScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CoronaStatsScheduler.class);

    private final CoronaVirusDataService coronaVirusDataService;
    private final CoronaStatsService coronaStatsService;

    public CoronaStatsScheduler(
            CoronaVirusDataService coronaVirusDataService,
            CoronaStatsService coronaStatsService
    ) {
        this.coronaVirusDataService = coronaVirusDataService;
        this.coronaStatsService = coronaStatsService;
    }

    @Scheduled(fixedDelay = 5000L)
    public void scheduled() {
        LOGGER.info("Sentry on work. Scanning corona situation...");
        List<CoronaStats> coronaStats = coronaVirusDataService.fetchVirusData();
        coronaStats.forEach(coronaStatsService::createOrUpdate);
    }


}