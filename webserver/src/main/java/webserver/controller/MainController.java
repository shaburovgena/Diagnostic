package webserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webserver.domain.User;
import webserver.repos.GroupRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")

public class MainController {

    private final GroupRepo groupRepo;

    @Value("${spring.profiles.active}")
    private String profile;
    private List<Map<String, String>> groups = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{ put("id", "1"); put("groupName", "First group"); }});
        add(new HashMap<String, String>() {{ put("id", "2"); put("groupName", "Second group"); }});
        add(new HashMap<String, String>() {{ put("id", "3"); put("groupName", "Third group"); }});
    }};
    @Autowired
    public MainController(GroupRepo groupRepo) {
        this.groupRepo = groupRepo;

    }

    @GetMapping
    public String main(
            Model model,
            @AuthenticationPrincipal User user
    ) {
        model.addAttribute("groups", groups);
        model.addAttribute("isDevMode", "dev".equals(profile));
        return "index";
    }
}