package webserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import webserver.domain.User;
import webserver.repos.SensorRepo;
import webserver.repos.UserRepo;

import java.security.Principal;
import java.util.HashMap;

@Controller
@RequestMapping("/")

public class MainController {

    private final SensorRepo sensorRepo;

    @Value("${spring.profiles.active}")
    private String profile;
    private UserRepo userRepo;

    @Autowired
    public MainController(SensorRepo sensorRepo,
                          UserRepo userRepo) {
        this.sensorRepo = sensorRepo;
        this.userRepo = userRepo;
    }

    @GetMapping
    public String main(
            Model model,
            @AuthenticationPrincipal User user

    ) {
        HashMap<Object, Object> data = new HashMap<>();

        if (user != null) {
            user.setPassword("");
            data.put("profile", user);
            data.put("sensors", sensorRepo.findAll());
        } else {
            data.put("sensors", "");
        }
        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));
        return "index";
    }


}