package Controller.sensors;

import Controller.Sensor;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;

import java.io.IOException;


public class GasSensor implements Sensor {
    //Module ADC121C_MQ9
    @Override
    public String meter() throws IOException, I2CFactory.UnsupportedBusNumberException {
        // Create I2C bus
        I2CBus Bus = I2CFactory.getInstance(I2CBus.BUS_1);
        // Get I2C device, ADC121C_MQ9 I2C address is 0x50(80)
        I2CDevice device = Bus.getDevice(0x50);

        // Read 2 bytes of data
        // raw_adc msb, raw_adc lsb
        byte[] data = new byte[2];
        device.read(0x00, data, 0, 2);

        // Convert the data to 12-bits
        int raw_adc = (((data[0] & 0x0F) * 256) + (data[1] & 0xFF));
        double ppm = ((1000.0 / 4096.0) * raw_adc) + 10;

        // Output data to screen
        return String.format("Gas Concentration : %.2f ppm %n", ppm);
    }

    //Module  ADC121C_MQ4
    public static void gasMethaneMeter() throws IOException, I2CFactory.UnsupportedBusNumberException {
        // Create I2C bus
        I2CBus Bus = I2CFactory.getInstance(I2CBus.BUS_1);
        // Get I2C device, ADC121C_MQ4 I2C address is 0x50(80)
        I2CDevice device = Bus.getDevice(0x50);

        // Read 2 bytes of data
        // raw_adc msb, raw_adc lsb
        byte[] data = new byte[2];
        device.read(0x00, data, 0, 2);

        // Convert the data to 12-bits
        int raw_adc = ((data[0] & 0x0F) * 256) + (data[1] & 0xFF);
        double ppm = ((10000.0 / 4096.0) * raw_adc) + 200;

        // Output data to screen
        System.out.printf("Methane Concentration : %.2f ppm %n", ppm);
    }
}
