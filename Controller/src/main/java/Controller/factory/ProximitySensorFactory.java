package Controller.factory;

import Controller.Sensor;
import Controller.SensorFactory;
import Controller.sensors.ProximitySensor;

//Module TMG39931
public class ProximitySensorFactory implements SensorFactory {

    @Override
    public Sensor createSensor() {
        return new ProximitySensor();
    }
}
