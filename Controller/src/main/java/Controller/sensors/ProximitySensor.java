package Controller.sensors;

import Controller.interfaces.Sensor;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;

import java.io.IOException;
//Module TMG39931
public class ProximitySensor implements Sensor {
    @Override
    public String meter() throws IOException, I2CFactory.UnsupportedBusNumberException, InterruptedException {
        // Create I2C bus
        I2CBus bus = I2CFactory.getInstance(I2CBus.BUS_1);
        // Get I2C device, TCS39931 I2C address is 0x39(57)
        I2CDevice device = bus.getDevice(0x39);

        // Set Wait Time register
        // Wtime = 2.78 ms,
        device.write(0x83, (byte) 0xFF);
        // Set Atime register
        // ATIME : 712ms, Max count = 65535 cycles
        device.write(0x81, (byte) 0x00);
        // Select enable register
        // Power ON, ALS enable, ProximitySensorFactory enable, Wait enable
        device.write(0x80, (byte) 0x0F);
        Thread.sleep(800);

        // Read 9 Bytes of Data
        // cData lsb, cData msb, red lsb, red msb, green lsb, green msb, blue lsb, blue msb, proximity
        byte[] data = new byte[9];
        device.read(0x94, data, 0, 9);

        // Convert the data
        int cData = (((data[1] & 0xFF) * 256) + (data[0] * 0xFF));
        int red = (((data[3] & 0xFF) * 256) + (data[2] & 0xFF));
        int green = (((data[5] & 0xFF) * 256) + (data[4] & 0xFF));
        int blue = (((data[7] & 0xFF) * 256) + (data[6]) & 0xFF);
        int proximity = data[8] & 0xFF;

        // Output data to screen
        String result = String.format("InfraRed luminance : %d lux %n", cData);
        result += String.format("Red Color luminance : %d lux %n", red);
        result += String.format("Green Color luminance : %d lux %n", green);
        result += String.format("Blue Color luminance : %d lux %n", blue);
        result += String.format("Proximity of the device : %d %n", proximity);
        return result;
    }
}
