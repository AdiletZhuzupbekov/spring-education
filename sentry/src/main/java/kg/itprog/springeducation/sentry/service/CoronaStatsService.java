package kg.itprog.springeducation.sentry.service;

import kg.itprog.springeducation.db.entity.CoronaStats;
import kg.itprog.springeducation.db.repository.CoronaStatsRepository;
import org.springframework.stereotype.Service;

@Service
public class CoronaStatsService {
    private final CoronaStatsRepository coronaStatsRepository;

    public CoronaStatsService(CoronaStatsRepository coronaStatsRepository) {
        this.coronaStatsRepository = coronaStatsRepository;
    }

    public CoronaStats createOrUpdate(
            CoronaStats coronaStats
    ) {
        CoronaStats source = coronaStatsRepository.findFirstByCountry(coronaStats.getCountry()).orElse(coronaStats);
        source.setLatestTotalCases(coronaStats.getLatestTotalCases() + 1);
        source.setDiffFromPreviousDay(coronaStats.getDiffFromPreviousDay() + 1);
        return coronaStatsRepository.save(source);
    }
}
