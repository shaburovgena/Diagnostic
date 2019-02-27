package webserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webserver.domain.GroupMetric;
import webserver.domain.Metric;
import webserver.repos.MetricRepo;
import webserver.service.FindSensorHelper;

import java.net.UnknownHostException;

@Controller
public class MetricController {

    @Autowired
    private MetricRepo metricRepo;
    @Autowired
    private FindSensorHelper scanner;

    private int port;
    private Iterable<Metric> metrics;

    @GetMapping("/group/{group}/scan")
    public String metricsView(Model model,
                              @PathVariable GroupMetric group
    ) {

        model.addAttribute("metrics", metrics);
        model.addAttribute("group", group);
        return "sensors";
    }


    @PostMapping("/group/{group}/scan")
    public String scaning(
            @PathVariable GroupMetric group,
            Model model,
            @RequestParam(name = "ipAddress", required = false) String ipAddress,
            @RequestParam(name = "port", required = false) int port

    ) throws UnknownHostException {

        scanner = new FindSensorHelper();
        this.port = port;
        scanner.scanNetwork(ipAddress, port);
        metrics = scanner.getMetrics();
        model.addAttribute("metrics", metrics);
        model.addAttribute("group", group);
        return "sensors";
    }

    @PostMapping("/group/{group}/metric")
    public String metricAdd(Model model,
                            @PathVariable GroupMetric group,
                            @RequestParam String title
    ) {
        // TODO: 26.02.2019 Изменить выбор через чекбоксы
        Metric metric = new Metric();
        metric.setTitle(title);
        metric.setPort(port);
        metric.setSelected(true);
        metric.setIpAddress(title);
        metric.setGroupMetric(group);
        metricRepo.save(metric);

        model.addAttribute("metrics", metrics);
        model.addAttribute("group", group);

        return "redirect:/group/{group}/scan";
    }
}
