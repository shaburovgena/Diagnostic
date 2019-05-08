package webserver.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webserver.domain.Metric;
import webserver.domain.User;
import webserver.domain.Views;
import webserver.repos.MetricRepo;

import java.util.List;

@RestController
@RequestMapping("sensor")
public class SensorController {

    private final MetricRepo metricRepo;

    @Autowired
    public SensorController(MetricRepo metricRepo) {
        this.metricRepo = metricRepo;
    }
@GetMapping
@JsonView(Views.IdTitle.class)
    public List<Metric> allMetricsView(
            @AuthenticationPrincipal User user
    ) {
        return (List<Metric>) metricRepo.findAll();
    }

}
