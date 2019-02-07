package webserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import webserver.domain.GroupMetric;
import webserver.domain.User;
import webserver.repos.GroupRepo;

import javax.validation.Valid;
import java.util.Map;

// TODO: 07.02.2019 Добавить переход между группами
@Controller
public class GroupController {

    @Autowired
    private GroupRepo groupRepo;

    @GetMapping("/group")
    public String viewGroups(Model model) {
        Iterable<GroupMetric> groups = groupRepo.findAll();
        model.addAttribute("groups", groups);
        return "group";
    }

    @PostMapping("/group")
    public String addGroup(
            @AuthenticationPrincipal User user,
            @Valid GroupMetric groupMetric,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("group", groupMetric);
        } else {
            groupMetric.setOwner(user);
            groupRepo.save(groupMetric);
        }
        Iterable<GroupMetric> groups = groupRepo.findAll();
        model.addAttribute("groups", groups);
        return "group";
    }

}