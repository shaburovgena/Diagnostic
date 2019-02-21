package webserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import webserver.domain.GroupMetric;
import webserver.domain.Metric;
import webserver.repos.MetricRepo;
import webserver.service.Scanner;

import java.net.SocketException;
import java.net.UnknownHostException;

@Controller
public class MetricController {

    @Autowired
    private MetricRepo metricRepo;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/group/{group}/scan")
    public String metricsView(Model model,
                              @PathVariable GroupMetric group,
                              @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC
                              ) Pageable pageable
    ){
        Page<Metric> page = metricRepo.findAll(pageable);
        model.addAttribute("group", group);
        model.addAttribute("page", page);

        return "metrics";
    }


    @PostMapping("/group/{group}/scan")
    public String scaning(
            @PathVariable GroupMetric group,
            Model model,
            @RequestParam(name = "ipAddress", required = false) String ipAddress,
            @RequestParam(name = "port", required = false) int port

    ) throws SocketException, UnknownHostException {

        Scanner scanner = new Scanner();
        scanner.scan(ipAddress, port);
        Iterable<Metric> metrics = scanner.getMetrics();
        model.addAttribute("metrics", metrics);
        model.addAttribute("group", group);
        return "metrics";
    }
}
