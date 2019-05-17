package webserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import webserver.domain.GroupSensor;
import webserver.domain.Sensor;
import webserver.domain.User;
import webserver.repos.GroupRepo;
import webserver.repos.SensorRepo;
import webserver.service.QueryMetrics;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

// TODO: 08.02.2019 Добавить отображение контента в соответствие с зависимостями
@Controller
@RequestMapping("/group")
public class GroupController {
    //Springs ExpLang который выдергивает из контекста директорию для загрузки
    //и вставляент ее в переменную uploadPath
    @Value("${upload.path}")
    private String uploadPath;
    @Autowired
    private GroupRepo groupRepo;
    @Autowired
    private SensorRepo sensorRepo;
    @Autowired
    private QueryMetrics queryMetrics;


    @GetMapping
    public String viewGroups(
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model
    ) {
        Iterable<GroupSensor> groups = null;

        //Если указан фильтр ищем группы по тегу
        if (filter != null && !filter.isEmpty()) {
            groups = groupRepo.findByGroupTag(filter);
        } else {
            groups = groupRepo.findAll();
        }

        //Добавляем в модель группы и фильтр
        model.addAttribute("filter", filter);

        model.addAttribute("groups", groups);


        return "groupList";
    }

    @PostMapping
    public String addGroup(
            @AuthenticationPrincipal User user,
            @RequestParam("file") MultipartFile file,
            @Valid GroupSensor groupSensor,
            BindingResult bindingResult,
            Model model
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("group", groupSensor);
        } else {
            saveFile(groupSensor, file);
            groupSensor.setOwner(user);
            groupRepo.save(groupSensor);
        }
        Iterable<GroupSensor> groups = groupRepo.findAll();
        model.addAttribute("groups", groups);
        return "groupList";
    }

    @PostMapping("{group}/delete")
    public String delete(
            @AuthenticationPrincipal User currentUser,
            @PathVariable GroupSensor group,
            Model model
    ) {
        if (group.getOwner().equals(currentUser) || currentUser.isAdmin()) {
            //Так как между таблицами есть связи сначала удаляем метрики привязанные к группе
            sensorRepo.deleteAllByGroupSensor(group);
            groupRepo.delete(group);
        }
        Iterable<GroupSensor> groups = groupRepo.findAll();
        model.addAttribute("groups", groups);
        return "groupList";
    }

    @GetMapping("user-groups/{user}")
    public String userGroups(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            Model model,
            @RequestParam(required = false) GroupSensor group
    ) {
        Set<GroupSensor> groups = user.getGroups();

        model.addAttribute("groups", groups);
        model.addAttribute("group", group);
        model.addAttribute("isCurrentUser", currentUser.equals(user));

        return "groupList";
    }

    @GetMapping("{group}")
    public String group(
            @PathVariable GroupSensor group,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC
            ) Pageable pageable,
            Model model
    ) {

        Iterable<Sensor> sensorsInGroup = sensorRepo.findByGroupSensor(group);
        queryMetrics.sendRequest(sensorsInGroup);
        Page<Sensor> page = sensorRepo.findByGroupSensor(group, pageable);
        model.addAttribute("page", page);
        return "groupView";
    }

    private void saveFile(@Valid GroupSensor groupSensor, @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            //Если директории нет, создаем ее
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            //Назначаем уникальное имя файлу с помощью randomUUID чтобы избежать коллизий
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            //Записываем полученный через форму файл
            file.transferTo(new File(uploadPath + "/" + resultFilename));

            groupSensor.setFilename(resultFilename);
        }
    }

    @PostMapping("/user-group/{user}")
    public String updateGroup(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long user,
            @RequestParam("id") GroupSensor group,
            @RequestParam("text") String text,
            @RequestParam("tag") String tag,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        if (group.getOwner().equals(currentUser)) {
            if (!StringUtils.isEmpty(text)) {
                group.setGroupName(text);
            }

            if (!StringUtils.isEmpty(tag)) {
                group.setGroupTag(tag);
            }

            saveFile(group, file);

            groupRepo.save(group);
        }

        return "redirect:/user-group/" + user;
    }
}
