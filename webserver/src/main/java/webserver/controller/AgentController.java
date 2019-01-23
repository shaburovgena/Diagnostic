package webserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webserver.domain.Metric;
import webserver.domain.MetricJson;
import webserver.repos.MetricRepo;

@RequestMapping("/agent")
@Controller
public class AgentController {

    @Autowired
    private MetricRepo metricRepo;

    @GetMapping
    public String metricsView(Model model,
                              @RequestParam(name = "time", required = false) Long time,
                              @RequestParam(name = "title", required = false) String title,
                              @RequestParam(name = "value", required = false) String value
    ) {
        if (time != null || title != null || value != null) {
            Metric metric = new Metric(time, title, value);
            metricRepo.save(metric);
        }
        Iterable<Metric> metrics = metricRepo.findAll();

        model.addAttribute("metrics", metrics);

        return "metrics";
    }


    @PostMapping
    public String fromAgent(
            Model model,
            @RequestParam(name = "time", required = false) Long time,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "value", required = false) String value
    ) {

        Metric metric = new Metric(time, title, value);

        metricRepo.save(metric);
        model.addAttribute("metrics", metricRepo.findAll());
        return "metrics";
    }
}
