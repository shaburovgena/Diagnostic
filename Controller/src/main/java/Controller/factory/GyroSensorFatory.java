package Controller.factory;

import Controller.Sensor;
import Controller.SensorFactory;
import Controller.sensors.GyroSensor;

//Module LSM9DS0
public class GyroSensorFatory implements SensorFactory {

    @Override
    public Sensor createSensor() {
        return new GyroSensor();
    }
}
