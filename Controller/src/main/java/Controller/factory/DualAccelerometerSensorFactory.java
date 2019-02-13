package Controller.factory;

import Controller.Sensor;
import Controller.SensorFactory;
import Controller.sensors.DualAccelerometerSensor;

//Module MXC6232xM
public class DualAccelerometerSensorFactory implements SensorFactory {
    @Override
    public Sensor createSensor() {
        return new DualAccelerometerSensor();
    }
}
