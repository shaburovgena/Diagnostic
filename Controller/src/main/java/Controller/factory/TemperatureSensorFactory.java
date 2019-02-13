package Controller.factory;

import Controller.Sensor;
import Controller.SensorFactory;
import Controller.sensors.TemperatureSensor;

//Module MCP9808
public class TemperatureSensorFactory implements SensorFactory {

    @Override
    public Sensor createSensor() {
        return new TemperatureSensor();
    }
}
