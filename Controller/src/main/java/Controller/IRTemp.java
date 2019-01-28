package Controller;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;

import java.io.IOException;
//Module TMP007
public class IRTemp {
    public static void term() throws IOException, I2CFactory.UnsupportedBusNumberException {
// Create I2CBus
        I2CBus bus = I2CFactory.getInstance(I2CBus.BUS_1);
        // Get I2C device, TMP007 I2C address is 0x41(64)
        I2CDevice device = bus.getDevice(0x41);

        // Select configuration register
        // Continuous conversion, comparator mode
        byte[] config = {0x15, 0x40};
        device.write(0x02, config, 0, 2);

        // Read 2 bytes of data from address 0x03(3)
        // temp msb, temp lsb
        byte[] data = new byte[2];
        device.read(0x03, data, 0, 2);

        // Convert the data to 14-bits
        int temp = (((data[0] & 0xFF) * 256 + (data[1] & 0xFC)) / 4);
        if(temp > 8191)
        {
            temp -= 16384;
        }
        double cTemp = temp * 0.03125;
        double fTemp = cTemp * 1.8 + 32;

        // Output data to screen
        System.out.printf("Temperature in Celsius : %.2f C %n", cTemp);
        System.out.printf("Temperature in Fahrenheit : %.2f C %n", fTemp);
    }

}
