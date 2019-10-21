package webserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webserver.domain.User;
import webserver.service.UserService;

import javax.validation.Valid;
import java.util.HashMap;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Value("${spring.profiles.active}")
    private String profile;
    @Autowired
    private UserService userService;

    @Autowired
    public RegisterController(
            UserService userService) {
        this.userService = userService;

    }
    @PostMapping()
    public String register(@RequestParam("passwordConfirm") String passwordConfirm,
                           @Valid User user,
                           Model model) {
        HashMap<Object, Object> data = new HashMap<>();
        if (user.getPassword() == null || passwordConfirm == null ||
                !user.getPassword().equals(passwordConfirm)) {
            data.put("errorMessage", "Check your credentials");
            data.put("isRegisterForm", "true");
            System.out.println("Проверьте правильность введенных данных");
        }else if (userService.addUser(user) != null) {
            data.put("isRegisterForm", "true");
            data.put("errorMessage", "User already exist");
            System.out.println("Пользователь существует");
        }else{
            data.put("isLoginForm", "true");
            data.put("errorMessage", "Welcome");
            System.out.println("Success");
        }
        model.addAttribute("profile", "null");
        model.addAttribute("groups", "[]");
        model.addAttribute("sensors", "[]");
        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));
        return "index";
    }

    @GetMapping()
    public String register(Model model) {
        model.addAttribute("profile", "null");
        model.addAttribute("groups", "[]");
        model.addAttribute("sensors", "[]");
        model.addAttribute("isDevMode", "dev".equals(profile));
        System.out.println("Redirect");
        return "index";

    }
}
