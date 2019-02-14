package Controller.factory;

import Controller.interfaces.Sensor;
import Controller.interfaces.SensorFactory;
import Controller.sensors.GasSensor;


public class GasSensorFactory implements SensorFactory {


    @Override
    public Sensor createSensor() {
        return new GasSensor();
    }
}
