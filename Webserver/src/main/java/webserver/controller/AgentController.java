package webserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import webserver.domain.Metric;
import webserver.domain.MetricJson;
import webserver.domain.dto.CaptchaResponseDto;
import webserver.repos.MetricRepo;

import java.util.Collections;

@RequestMapping("/agent")
@Controller
public class AgentController {

    @Autowired
    private MetricRepo metricRepo;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public String metricsView(Model model,
                              @RequestParam(name = "time", required = false) Long time,
                              @RequestParam(name = "title", required = false) String title,
                              @RequestParam(name = "value", required = false) String value,
                              @PageableDefault (sort = {"id"}, direction = Sort.Direction.DESC
                              )Pageable pageable
                              ) {
        if (time != null || title != null || value != null) {
            Metric metric = new Metric(time, title, value);
            metricRepo.save(metric);
        }
        String url = String.format("http://localhost:8443/check");
        System.out.println(url);
        MetricJson response = restTemplate.postForObject(url, Collections.emptyList(), MetricJson.class);
        System.out.println(response.getTitle());

//        Page<Metric> page = metricRepo.findAll(pageable);
//        model.addAttribute("url", "/agent");
//        model.addAttribute("page", page);

        return "metrics";
    }


    @PostMapping
    public String fromAgent(
            Model model,
            @RequestParam(name = "time", required = false) Long time,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "value", required = false) String value,
            @RequestBody (required = false) MetricJson metricJson

    ) {

        Metric metric = new Metric(time, title, value);
        System.out.println(metricJson.toString());
        metricRepo.save(metric);
        model.addAttribute("metrics", metricRepo.findAll());
        return "metrics";
    }
}
