package webserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webserver.domain.User;
import webserver.repos.GroupRepo;
import webserver.repos.SensorRepo;
import webserver.service.UserService;

import javax.validation.Valid;
import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {

    private final SensorRepo sensorRepo;
    @Value("${spring.profiles.active}")
    private String profile;
    private GroupRepo groupRepo;
    @Autowired
    private UserService userService;
    @Autowired
    public MainController(SensorRepo sensorRepo,
                          UserService userService, GroupRepo groupRepo) {
        this.sensorRepo = sensorRepo;
        this.userService = userService;
        this.groupRepo = groupRepo;
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
            data.put("groups", groupRepo.findAll());
        } else {
            data.put("sensors", "");
            data.put("groups", "");
        }
        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));
        return "index";
    }

    @PostMapping("/register")
    public String register(@RequestParam("passwordConfirm") String passwordConfirm,
                           @Valid User user,
                           Model model) {


        HashMap<Object, Object> data = new HashMap<>();
        if (user.getPassword() == null || passwordConfirm == null ||
                !user.getPassword().equals(passwordConfirm)) {
            data.put("errorMessage", "Проверьте правильность введенных данных");
            data.put("isRegisterForm", true);
            data.put("sensors", "");
            data.put("groups", "");
            model.addAttribute("frontendData", data);
            return "index";
        }
        if (userService.addUser(user) != null) {
            return "redirect:/login?error";
        }
        return "redirect:/register";
    }
        @GetMapping("/register")
    public String register(Model model,
                           @AuthenticationPrincipal User user) {
        HashMap<Object, Object> data = new HashMap<>();


            data.put("profile", user);

            data.put("sensors", "");
            data.put("groups", "");

        model.addAttribute("frontendData", data);
        return "index";

    }
}