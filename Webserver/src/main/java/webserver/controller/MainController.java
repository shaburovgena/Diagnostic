package webserver.controller;

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
    ) {
        if (user != null) {
            Iterable<Metric>sensors = metricRepo.findAll();
            model.addAttribute("sensors", sensors);
        }else {
            model.addAttribute("sensors", "[Sensors is undefined]");
        }
        model.addAttribute("profile", user);
        model.addAttribute("isDevMode", "dev".equals(profile));
        return "index";
    }
}