package webserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import webserver.domain.Sensor;
import webserver.repos.MetricRepo;
import webserver.service.Scanner;

import java.net.SocketException;
import java.net.UnknownHostException;
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
                              @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC
                              ) Pageable pageable
    ) throws SocketException, UnknownHostException {
        if (time != null || title != null || value != null) {
            Sensor sensor = new Sensor(time, title, value);
            metricRepo.save(sensor);
        }
//        String url = String.format("http://localhost:8443/check");
//        System.out.println(url);
//        Sensor response = restTemplate.postForObject(url, Collections.emptyList(), Sensor.class);
//        System.out.println(response.getTitle());
        Scanner scanner = new Scanner();
        scanner.scan();

//        Page<Sensor> page = metricRepo.findAll(pageable);
//        model.addAttribute("url", "/agent");
//        model.addAttribute("page", page);

        return "greeting";
    }


    @PostMapping
    public String fromAgent(
            Model model,
            @RequestParam(name = "time", required = false) Long time,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "value", required = false) String value

    ) {

        Sensor sensor = new Sensor(time, title, value);
        metricRepo.save(sensor);
        model.addAttribute("metrics", metricRepo.findAll());
        return "metrics";
    }
}
