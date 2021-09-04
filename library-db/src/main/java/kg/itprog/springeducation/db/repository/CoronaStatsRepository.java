package kg.itprog.springeducation.db.repository;

import kg.itprog.springeducation.db.entity.CoronaStats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoronaStatsRepository extends JpaRepository<CoronaStats, Long> {

    Optional<CoronaStats> findFirstByCountry(String country);
}
