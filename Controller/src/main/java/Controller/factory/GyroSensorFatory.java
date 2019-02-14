package Controller.factory;

import Controller.interfaces.Sensor;
import Controller.interfaces.SensorFactory;
import Controller.sensors.GyroSensor;

//Module LSM9DS0
public class GyroSensorFatory implements SensorFactory {

    @Override
    public Sensor createSensor() {
        return new GyroSensor();
    }
}
