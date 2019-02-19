package webserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import webserver.domain.Metric;
import webserver.repos.MetricRepo;
import webserver.service.Scanner;

import java.net.SocketException;
import java.net.UnknownHostException;

@RequestMapping("/agent")
@Controller
public class AgentController {

    @Autowired
    private MetricRepo metricRepo;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public String sensorsView(Model model,
                              @RequestParam(name = "time", required = false) Long time,
                              @RequestParam(name = "title", required = false) String title,
                              @RequestParam(name = "value", required = false) String value,
                              @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC
                              ) Pageable pageable
    ) throws SocketException, UnknownHostException {
        Page<Metric> page = metricRepo.findAll(pageable);
        model.addAttribute("page", page);
        return "sensors";
    }


    @PostMapping
    public String scaning(
            Model model,
            @RequestParam(name = "ipAddress", required = false) String ipAddress,
            @RequestParam(name = "port", required = false) int port

    ) throws SocketException, UnknownHostException {

        Scanner scanner = new Scanner();
        scanner.scan("", port);
        return "redirect:/greeting";
    }
}
