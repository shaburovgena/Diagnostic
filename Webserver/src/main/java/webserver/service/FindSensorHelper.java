package webserver.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import webserver.domain.Sensor;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

@Service
public class FindSensorHelper {

    //Сканирование подсети сервера для поиска сенсоров
    //Чтобы выдавать их списком при добавлении в группу

    private Set<Sensor> sensors;
    private Set<String> interfaces;
    private InetAddress hostname;
    private byte[] buf;
    private Sensor sensor;

    public FindSensorHelper() {
        sensors = new HashSet<>();
    }

    //Получение всех интерфейсов для определения какую подсеть сканировать
    public void getAllInterfaces() throws SocketException {
        Enumeration e = NetworkInterface.getNetworkInterfaces();
        while (e.hasMoreElements()) {
            NetworkInterface n = (NetworkInterface) e.nextElement();
            Enumeration ee = n.getInetAddresses();
            while (ee.hasMoreElements()) {
                InetAddress i = (InetAddress) ee.nextElement();
                interfaces.add(i.getHostAddress());
            }
        }

    }

    //Сканирование выбранного диапазона или хоста
    @Async
    public void scanNetwork(String ipAddress, int port) throws UnknownHostException {
        getHostname(ipAddress);

        if ((0xff & buf[3]) != 0 && (0xff & buf[3]) != 255 && !ipAddress.isEmpty()) {
            String host = (0xff & buf[0]) + "." + (0xff & buf[1]) + "." + (0xff & buf[2]) + "." + (0xff & buf[3]);
            checkConnection( host, port);
        } else {
            for (int i = 1; i < 255; i++) {

                String host = (0xff & buf[0]) + "." + (0xff & buf[1]) + "." + (0xff & buf[2]) + "." + i;
                checkConnection( host, port);
            }
        }
    }

    private void checkConnection(String host, int port) {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(host, port), 20);
            socket.close();
            System.out.println("Connect: " + host + ":" + port);
            sensor = new Sensor();
            sensor.setIpAddress(host);
            sensor.setTitle(host);
            sensor.setPort(port);
            sensors.add(sensor);
        } catch (IOException e) {
            System.out.println("Could not connect to host " + host + " on port: " + port);
        }
    }

    private void getHostname(String ipAddress) throws UnknownHostException {
        hostname = InetAddress.getByName(Inet4Address.getLocalHost().getHostAddress());

        if (!ipAddress.isEmpty()) {
            hostname = InetAddress.getByName(ipAddress);
        }

        buf = hostname.getAddress();
    }


    public Set<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(Set<Sensor> sensors) {
        this.sensors = sensors;
    }

    public Set<String> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(Set<String> interfaces) {
        this.interfaces = interfaces;
    }


}
