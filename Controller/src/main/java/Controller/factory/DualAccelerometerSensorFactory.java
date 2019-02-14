package Controller.factory;

import Controller.interfaces.Sensor;
import Controller.interfaces.SensorFactory;
import Controller.sensors.DualAccelerometerSensor;

//Module MXC6232xM
public class DualAccelerometerSensorFactory implements SensorFactory {
    @Override
    public Sensor createSensor() {
        return new DualAccelerometerSensor();
    }
}
