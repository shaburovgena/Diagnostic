package Controller.service;

import Controller.interfaces.SensorFactory;
import Controller.factory.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class CreateSensorService {

//    @Value("${active.sensor}")
//    private String sensorType;

    // TODO: 13.02.2019 Добавить проверку на подключенные модули
    public SensorFactory createSensorByType(String sensorType) {
        if (sensorType.equalsIgnoreCase("TemperatureSensor")) {
            return new TemperatureSensorFactory();
        } else if (sensorType.equalsIgnoreCase("DualAccelerometerSensor")) {
            return new DualAccelerometerSensorFactory();
        } else if (sensorType.equalsIgnoreCase("GasSensor")) {
            return new GasSensorFactory();
        } else if (sensorType.equalsIgnoreCase("GyroSensor")) {
            return new GyroSensorFatory();
        } else if (sensorType.equalsIgnoreCase("IRTempSensor")) {
            return new IRTempSensorFactory();
        } else if (sensorType.equalsIgnoreCase("PressureSensor")) {
            return new PressureSensorFactory();
        } else if (sensorType.equalsIgnoreCase("ProximitySensor")) {
            return new ProximitySensorFactory();
        } else {
            throw new RuntimeException("Unknown sensor type");
        }
    }

}
