package webserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webserver.domain.Metric;
import webserver.domain.User;
import webserver.repos.MetricRepo;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {

    private final MetricRepo metricRepo;

    @Value("${spring.profiles.active}")
    private String profile;

    @Autowired
    public MainController(MetricRepo metricRepo) {
        this.metricRepo = metricRepo;

    }

    @GetMapping
    public String main(
            Model model,
            @AuthenticationPrincipal User user
    ) throws JsonProcessingException {
        HashMap<Object, Object> data = new HashMap<>();
        if (user != null) {
            data.put("profile", user);
            Iterable<Metric> messages = metricRepo.findAll();
            model.addAttribute("messages", messages);
        }else {
            model.addAttribute("messages", "[]");
        }
        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));
        return "index";
    }
}
