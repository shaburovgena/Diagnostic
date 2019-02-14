package Controller.controller;

import Controller.interfaces.Sensor;
import Controller.interfaces.SensorFactory;
import Controller.service.CreateSensorService;
import com.pi4j.io.i2c.I2CFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/sensor")
public class MainController {

    @Autowired
    private CreateSensorService sensorService;


    @PostMapping
    public JSONObject main(
            @PathVariable String sensorType
    ) throws InterruptedException, IOException, I2CFactory.UnsupportedBusNumberException {

        SensorFactory sensorFactory = sensorService.createSensorByType(sensorType);
        Sensor sensor = sensorFactory.createSensor();
        sensor.meter();
        return new JSONObject();
    }

}
