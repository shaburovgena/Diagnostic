package webserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import webserver.domain.GroupMetric;
import webserver.domain.Metric;
import webserver.domain.User;
import webserver.repos.GroupRepo;
import webserver.repos.MetricRepo;

import javax.validation.Valid;
import java.util.Map;
import java.util.Set;

// TODO: 08.02.2019 Добавить отображение контента в соответствие с зависимостями
@Controller
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupRepo groupRepo;
    @Autowired
    private MetricRepo metricRepo;

    @GetMapping
    public String viewGroups(Model model) {
        Iterable<GroupMetric> groups = groupRepo.findAll();
        model.addAttribute("groups", groups);
        return "groupList";
    }

    @PostMapping
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
        return "groupList";
    }

    @PostMapping("delete/{group}")
    public String delete(
            @AuthenticationPrincipal User currentUser,
            @PathVariable GroupMetric group,
            Model model
    ) {
        if (group.getOwner().equals(currentUser)) {
            groupRepo.delete(group);
        }
        Iterable<GroupMetric> groups = groupRepo.findAll();
        model.addAttribute("groups", groups);
        return "groupList";
    }

    @GetMapping("{group}")
    public String group(
            @PathVariable GroupMetric group,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC
            ) Pageable pageable,
            Model model
    ) {

        Page<Metric> page = metricRepo.findByGroupMetric(group, pageable);
        model.addAttribute("page", page);
        return "groupView";
    }
}
