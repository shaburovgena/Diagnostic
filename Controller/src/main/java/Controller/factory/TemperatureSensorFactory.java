package Controller.factory;

import Controller.interfaces.Sensor;
import Controller.interfaces.SensorFactory;
import Controller.sensors.TemperatureSensor;

//Module MCP9808
public class TemperatureSensorFactory implements SensorFactory {

    @Override
    public Sensor createSensor() {
        return new TemperatureSensor();
    }
}
