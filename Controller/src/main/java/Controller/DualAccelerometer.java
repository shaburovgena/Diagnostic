package Controller;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import java.io.IOException;

//Module MXC6232xM
public class DualAccelerometer {
    public static void accelMeter() throws IOException, I2CFactory.UnsupportedBusNumberException, InterruptedException {
        // Create I2C bus
        I2CBus Bus = I2CFactory.getInstance(I2CBus.BUS_1);
        // Get I2C device, device I2C address is 0x10(16)
        I2CDevice device = Bus.getDevice(0x10);

        // Sets up the accelerometer to begin reading.
        device.write((byte) 0x00);
        Thread.sleep(500);

        // Read data from output register 0x00(0)
        // Status, X msb, X lsb, Y msb, Y lsb
        byte[] data = new byte[5];
        device.read(data, 0, 5);

        // Convert the values
        int xAccl = ((data[1] & 0x0F) * 256 + (data[2] & 0xFF));
        if (xAccl > 2047) {
            xAccl = xAccl - 4096;
        }

        int yAccl = ((data[3] & 0x0F) * 256 + (data[4] & 0xFF));
        if (yAccl > 2047) {
            yAccl = yAccl - 4096;
        }

        // Output data to screen
        System.out.printf("X-Axis : %d %n", xAccl);
        System.out.printf("Y-Axis : %d %n", yAccl);
    }
}
