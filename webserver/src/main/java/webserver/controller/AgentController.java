package webserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webserver.domain.Metric;
import webserver.domain.MetricJson;
import webserver.repos.MetricRepo;

@Controller
public class AgentController {

    @Autowired
    private MetricRepo metricRepo;

    @GetMapping("/agent")
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

    @RequestMapping(value = "/agent", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    @PostMapping()
    public String fromAgent(
            @RequestBody MetricJson metricJson
    ) {
        Metric metric = new Metric(metricJson.getTime(), metricJson.getTitle(), metricJson.getValue());

        metricRepo.save(metric);
        System.out.println("Receive post request from Agent " + metricJson.toString());

        return "metrics";
    }
}
