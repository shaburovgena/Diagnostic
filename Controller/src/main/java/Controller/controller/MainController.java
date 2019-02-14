package Controller.controller;

import Controller.interfaces.Sensor;
import Controller.interfaces.SensorFactory;
import Controller.service.CreateSensorService;
import com.pi4j.io.i2c.I2CFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller

public class MainController {

    @Autowired
    private CreateSensorService sensorService;


    @PostMapping("/sensor")
    public JSONObject main(
            @PathVariable String sensorType
    ) throws InterruptedException, IOException, I2CFactory.UnsupportedBusNumberException {

        SensorFactory sensorFactory = sensorService.createSensorByType(sensorType);
        Sensor sensor = sensorFactory.createSensor();
        sensor.meter();
        return new JSONObject();
    }

    //Проверка работы сенсора
    @GetMapping("/check")
    public JSONObject check(

    ) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", "testMetricRest");
        jsonObject.put("time", "----");
        jsonObject.put("value", "888");

        return jsonObject;
    }


}
