package webserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webserver.domain.GroupSensor;
import webserver.domain.Sensor;
import webserver.repos.SensorRepo;
import webserver.service.FindSensorHelper;

import java.net.UnknownHostException;

@Controller
public class SensorController {

    @Autowired
    private SensorRepo sensorRepo;
    @Autowired
    private FindSensorHelper scanner;
    // Корявый фронтэнд не умеет передавать скрытый input типа number,
// а строкой передает с хз каким разделителем.
// Пришлось объявлять переменную для записи в бд
    private int port;
    private Iterable<Sensor> sensors;


    @GetMapping("/group/{group}/scan")
    public String sensorsView(Model model,
                              @PathVariable GroupSensor group
    ) {
//Имя sensors занято в пространстве имен FTL, пришлось использовать другое имя
        model.addAttribute("sensors1", sensors);
        model.addAttribute("group", group);
        return "sensors";
    }


    @PostMapping("/group/{group}/scan")
    public String scaning(
            @PathVariable GroupSensor group,
            Model model,
            @RequestParam(name = "ipAddress", required = false) String ipAddress,
            @RequestParam(name = "port", required = false) int port

    ) throws UnknownHostException {

        scanner = new FindSensorHelper();
        this.port = port;
        scanner.scanNetwork(ipAddress, port);
        sensors = scanner.getSensors();
        model.addAttribute("sensors1", sensors);
        model.addAttribute("group", group);
        return "sensors";
    }

    @PostMapping("/group/{group}/sensor")
    public String sensorAdd(Model model,
                            @PathVariable GroupSensor group,
                            @RequestParam String title
    ) {
        // TODO: 26.02.2019 Изменить выбор через чекбоксы
        Sensor sensor = new Sensor();
        sensor.setTitle(title);
        sensor.setPort(port);
        sensor.setSelected(true);
        sensor.setIpAddress(title);
        sensor.setGroupSensor(group);
        sensorRepo.save(sensor);

        model.addAttribute("sensors1", sensors);
        model.addAttribute("group", group);

        return "redirect:/group/{group}/scan";
    }
}
