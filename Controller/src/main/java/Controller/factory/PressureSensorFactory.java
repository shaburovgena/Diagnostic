package Controller.factory;

import Controller.interfaces.Sensor;
import Controller.interfaces.SensorFactory;
import Controller.sensors.PressureSensor;

//Module CPS120
public class PressureSensorFactory implements SensorFactory {

    @Override
    public Sensor createSensor() {
        return new PressureSensor();
    }
}
