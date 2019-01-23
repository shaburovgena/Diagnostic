package webserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import webserver.domain.Message;
import webserver.domain.User;
import webserver.repos.MessageRepo;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;
    //Springs ExpLang который выдергивает из контекста директорию для загрузки
    //и вставляент ее в переменную uploadPath
    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {

        //Добавляем в коллекцию все сообщения из БД
        Iterable<Message> messages = messageRepo.findAll();

        //Если указан фильтр ищем сообщения по тегу
        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }

        //Добавляем в модель сообщения и фильтр
        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);

        //Возвращаем страницу main
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Message message,
            //ОБЯЗАТЕЛЬНО BindingResult должен идти перед Model, иначе весь стек будет валиться во view
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        message.setAuthor(user);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("message", message);
        } else {
            //Если указан файл создаем новый файл в директории
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

                message.setFilename(resultFilename);
            }

            model.addAttribute("message", null);
            //Сохраняем полученное сообщение в БД
            messageRepo.save(message);

        }
        //Получаем все сообщения из БД
        Iterable<Message> messages = messageRepo.findAll();

        //Отправляем полученные сообщения в форму main.ftl
        model.addAttribute("messages", messages);

        return "main";
    }

}
