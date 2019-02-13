package Controller.factory;

import Controller.Sensor;
import Controller.SensorFactory;
import Controller.sensors.GasSensor;


public class GasSensorFactory implements SensorFactory {


    @Override
    public Sensor createSensor() {
        return new GasSensor();
    }
}
