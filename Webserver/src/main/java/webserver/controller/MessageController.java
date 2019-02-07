package webserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import webserver.domain.Message;
import webserver.repos.MessageRepo;

import java.util.Map;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageRepo messageRepo;

    //Просмотреть сообщение польностью
    @GetMapping("{message}")
    public String message(Model model,
                          @PathVariable Message message) {
        model.addAttribute("message", message);
        return "message";
    }

    //Отредактировать сообщение
    @GetMapping("{message}/edit")
    public String messageEdit(
            @PathVariable(required = false) Message message,
            Model model) {
        model.addAttribute("message", message);
        return "messageEdit";
    }

    @PostMapping
    public String messageSave(
            BindingResult bindingResult,
            Model model,
            @RequestParam String text,
            @RequestParam("id") Message message,
            @RequestParam String tag) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("message", message);
        }

        message.setText(text);
        message.setTag(tag);

        messageRepo.save(message);

        System.out.println(message.toString());

        return "redirect:/message/" + message.getId();
    }
}
