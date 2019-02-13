package Controller.factory;

import Controller.Sensor;
import Controller.SensorFactory;
import Controller.sensors.IRTempSensor;

//Module TMP007
public class IRTempSensorFactory implements SensorFactory {

    @Override
    public Sensor createSensor() {
        return new IRTempSensor();
    }
}
