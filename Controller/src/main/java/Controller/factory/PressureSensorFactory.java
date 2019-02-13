package Controller.factory;

import Controller.Sensor;
import Controller.SensorFactory;
import Controller.sensors.PressureSensor;

//Module CPS120
public class PressureSensorFactory implements SensorFactory {

    @Override
    public Sensor createSensor() {
        return new PressureSensor();
    }
}
