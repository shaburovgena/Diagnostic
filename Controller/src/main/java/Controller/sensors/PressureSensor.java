package Controller.sensors;

import Controller.Sensor;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;

import java.io.IOException;
//Module CPS120
public class PressureSensor implements Sensor {
    @Override
    public  String meter() throws IOException, I2CFactory.UnsupportedBusNumberException, InterruptedException {
        // Create I2CBus
        I2CBus bus = I2CFactory.getInstance(I2CBus.BUS_1);
        // Get I2C device, CPS120 I2C address is 0x28(40)
        I2CDevice device = bus.getDevice(0x28);

        // Send start command
        device.write(0x28, (byte)0x80);
        Thread.sleep(800);

        // Read 2 bytes of data, msb first
        byte[] data = new byte[2];
        device.read(data, 0, 2);

        // Convert data to kPa
        double pressure = (((data[0] & 0x3F) * 256 + data[1]) * (90 / 16384.00)) + 30;

        // Output data to screen
        return String.format("Pressure is : %.2f kPa %n",pressure);
    }
}
