package webserver.restController;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import webserver.domain.GroupSensor;
import webserver.domain.Sensor;
import webserver.domain.User;
import webserver.domain.Views;
import webserver.repos.GroupRepo;
import webserver.repos.SensorRepo;

import java.util.List;

@RestController
public class PanelController {

    private final SensorRepo sensorRepo;
    private final GroupRepo groupRepo;


    @Autowired
    public PanelController(SensorRepo sensorRepo, GroupRepo groupRepo) {
        this.sensorRepo = sensorRepo;
        this.groupRepo = groupRepo;
    }


    @GetMapping("sensor")
    @JsonView(Views.IdNameValueGroupPort.class)
    public List<Sensor> allSensorsView(
            @AuthenticationPrincipal User user
    ) {
        return (List<Sensor>) sensorRepo.findAll();
    }

    @MessageMapping("/changeGroup")
    @SendTo("/topic/activity")
    @GetMapping("panel")
    @JsonView(Views.IdName.class)
    public List<GroupSensor> groupsView(

    ) {
        return (List<GroupSensor>) groupRepo.findAll();
    }


}
