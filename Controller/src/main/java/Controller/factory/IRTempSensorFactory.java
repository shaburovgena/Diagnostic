package Controller.factory;

import Controller.interfaces.Sensor;
import Controller.interfaces.SensorFactory;
import Controller.sensors.IRTempSensor;

//Module TMP007
public class IRTempSensorFactory implements SensorFactory {

    @Override
    public Sensor createSensor() {
        return new IRTempSensor();
    }
}
