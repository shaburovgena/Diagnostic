package Controller;

import com.pi4j.io.i2c.I2CFactory;

import java.io.IOException;

public interface Sensor {
    String meter() throws IOException, I2CFactory.UnsupportedBusNumberException, InterruptedException;
}
