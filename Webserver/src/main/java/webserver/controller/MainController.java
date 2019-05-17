package webserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webserver.domain.Sensor;
import webserver.domain.User;
import webserver.repos.SensorRepo;

@Controller
@RequestMapping("/")
public class MainController {

    private final SensorRepo sensorRepo;

    @Value("${spring.profiles.active}")
    private String profile;

    @Autowired
    public MainController(SensorRepo sensorRepo) {
        this.sensorRepo = sensorRepo;

    }

    @GetMapping
    public String main(
            Model model,
            @AuthenticationPrincipal User user
    ) {
        if (user != null) {
            Iterable<Sensor>sensors = sensorRepo.findAll();
            model.addAttribute("sensors", sensors);
        }else {
            model.addAttribute("sensors", "[Sensors is undefined]");
        }
        model.addAttribute("profile", user);
        model.addAttribute("isDevMode", "dev".equals(profile));
        return "index";
    }
}