package webserver.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webserver.domain.Sensor;
import webserver.domain.User;
import webserver.domain.Views;
import webserver.repos.SensorRepo;

import java.util.List;

@RestController
@RequestMapping("sensor")
public class PanelController {

    private final SensorRepo sensorRepo;

    @Autowired
    public PanelController(SensorRepo sensorRepo) {
        this.sensorRepo = sensorRepo;
    }

    @GetMapping
    @JsonView(Views.IdTitleValueGroupPort.class)
    public List<Sensor> allSensorsView(
            @AuthenticationPrincipal User user
    ) {
        return (List<Sensor>) sensorRepo.findAll();
    }

}
