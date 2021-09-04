package kg.itprog.springeducation.sentry;

import kg.itprog.springeducation.db.entity.CoronaStats;
import kg.itprog.springeducation.db.repository.CoronaStatsRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EntityScan(basePackageClasses = CoronaStats.class)
@EnableJpaRepositories(basePackageClasses = CoronaStatsRepository.class)
public class SentryApplication {

    public static void main(String... args) {
        SpringApplication.run(SentryApplication.class, args);
    }

}
