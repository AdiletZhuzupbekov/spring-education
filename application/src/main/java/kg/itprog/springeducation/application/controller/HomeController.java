package kg.itprog.springeducation.application.controller;

import kg.itprog.springeducation.db.entity.CoronaStats;
import kg.itprog.springeducation.db.repository.CoronaStatsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private final CoronaStatsRepository coronaStatsRepository;

    public HomeController(
            CoronaStatsRepository coronaStatsRepository
    ) {
        this.coronaStatsRepository = coronaStatsRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<CoronaStats> allStats = coronaStatsRepository.findAll();
        int totalCases = allStats.stream().mapToInt(CoronaStats::getLatestTotalCases).sum();
        int totalNewCases = allStats.stream().mapToInt(CoronaStats::getDiffFromPreviousDay).sum();

        model.addAttribute("title", "CoVid-19 Tracker Application");
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalCases);
        model.addAttribute("totalNewCases", totalNewCases);
        return "home";
    }

}
