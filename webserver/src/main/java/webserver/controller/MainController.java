package webserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webserver.domain.User;
import webserver.domain.Views;
import webserver.repos.GroupRepo;
import webserver.repos.SensorRepo;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {

    private final SensorRepo sensorRepo;
    private final ObjectWriter sensorWriter;
    @Value("${spring.profiles.active}")
    private String profile;
    private final ObjectWriter groupWriter;
    private final ObjectWriter profileWriter;
    private GroupRepo groupRepo;


    @Autowired
    public MainController(SensorRepo sensorRepo,
                          GroupRepo groupRepo, ObjectMapper mapper) {
        this.sensorRepo = sensorRepo;

        this.groupRepo = groupRepo;

        ObjectMapper objectMapper = mapper
                .setConfig(mapper.getSerializationConfig());
        this.groupWriter = objectMapper
                .writerWithView(Views.IdNameValueAttribute.class);
        this.sensorWriter = objectMapper
                .writerWithView(Views.IdNameValueAttribute.class);
        this.profileWriter = objectMapper
                .writerWithView(Views.IdNameRoles.class);

    }

    @GetMapping
    public String main(
            Model model,
            @AuthenticationPrincipal User user
    ) throws JsonProcessingException {
        HashMap<Object, Object> data = new HashMap<>();
        if (user != null && user.getActivationCode() == null) {
            model.addAttribute("profile", profileWriter.writeValueAsString(user));
            model.addAttribute("sensors", sensorWriter.writeValueAsString(sensorRepo.findAll()));
            model.addAttribute("groups", groupWriter.writeValueAsString(groupRepo.findAll()));
            data.put("errorMessage", "Hello");
            model.addAttribute("frontendData", data);
        } else {
            model.addAttribute("profile", "null");
            model.addAttribute("groups", "[]");
            model.addAttribute("sensors", "[]");
            data.put("errorMessage", "Hello");
            data.put("isLoginForm", "true");
            model.addAttribute("frontendData", data);
        }

        model.addAttribute("isDevMode", "dev".equals(profile));
        return "index";
    }


}