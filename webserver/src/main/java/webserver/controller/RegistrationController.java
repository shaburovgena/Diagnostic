package webserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import webserver.domain.CaptchaResponse;
import webserver.domain.User;
import webserver.service.UserService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify" +
            "?secret=%s" + "&response=%s";

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${recaptcha.secret}")
    private String secret;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(
            @RequestParam("passwordConfirm") String passwordConfirm,
            @RequestParam("g-recaptcha-response") String captchaResponse,
            //Черная магия позволяет получить сразу user из переданного username
            @Valid User user,
            BindingResult bindingResult,
            Model model
    ) {

        String url = String.format(CAPTCHA_URL, secret, captchaResponse);
        CaptchaResponse response = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponse.class);

        if (!response.isSuccess()) {
            model.addAttribute("captchaError", "Ошибка в капче");
        }

        if (user.getPassword() == null || passwordConfirm == null ||
                !user.getPassword().equals(passwordConfirm) || !response.isSuccess()) {
            model.addAttribute("passwordError", "Пароли указаны с ошибкой");
        }

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);

            return "registration";
        }


        //Если пользователь уже существует в базе возвращаем в форму "Пользователь с таким именем существует"
        if (userService.addUser(user) != null) {
            model.addAttribute("usernameError", "Пользователь с таким именем существует");
            return "registration";
        }

        //Возвращаем на страницу login
        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);
        System.out.println(code);
        if (isActivated) {
            model.addAttribute("message", "Активация аккаунта прошла успешно");
        } else {
            model.addAttribute("message", "Активация аккаунта не выполнена");
        }

        return "login";
    }
}