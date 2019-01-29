package webserver.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webserver.domain.Role;
import webserver.domain.User;
import webserver.service.UserService;

import java.util.Map;

@Controller
/* Для обработки запросов на этом контроллере,
пользователь должен быть авторизован с ролью ADMIN
* */
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/user") //Позволяет не указывать отдельный маппинг для каждого метода
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping //Маппинг уже указан в @RequestMapping ("/user")
    public String userList(Model model) {

        //Отдать в форму всех пользователей из БД
        model.addAttribute("users", userService.findAll());

        return "userList";
    }

    @GetMapping("{user}") //Маппинг по дополнительному пути, полученному от формы "/user/${user.id}"
    public String userEdit(
            //По user.id мы неведомым образом получаем польноценного user
            @PathVariable User user,
            Model model) {

        //Отдаем в форму userEdit пользователя и его роли
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        return "userEdit";
    }

    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam("userId") User user,
            @RequestParam Map<String, String> form
    ) {
        userService.saveUser(username, user, form);

        //Возвращаем к списку пользователей
        return "redirect:/user";
    }

    //В этот маппинг могут попадать пользователи авторизованные как USER
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/profile")
    public String getProfile(
            Model model,
            //Позволяет получить пользователя из контекста, а не из базы
            @AuthenticationPrincipal User user
    ) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());

        return "profile";
    }

    //В этот маппинг могут попадать пользователи авторизованные как USER
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/profile")
    public String updateProfile(@AuthenticationPrincipal User user,
                                @RequestParam String password,
                                @RequestParam String email) {

        userService.updateUser(user, password, email);

        return "redirect:/user/profile";
    }

}
