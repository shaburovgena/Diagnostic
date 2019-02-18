package webserver.service;

import org.springframework.scheduling.annotation.Async;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;
import java.util.Set;

public class Scanner {

    //Сканирование подсети сервера для поиска сенсоров
    //Чтобы выдавать их списком при добавлении в группу

    private Set<String> sensors;
    private Set<String> interfaces;

    private void getAllInterfaces() throws SocketException {
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
@Async
    public void scan() throws SocketException, UnknownHostException {

        InetAddress localhost = InetAddress.getByName(Inet4Address.getLocalHost().getHostAddress());
        byte[] buf = localhost.getAddress();
        for (int i = 0; i < buf.length; i++) {
            System.out.println(buf[i]);
        }
        for (int i = 1; i < 255; i++) {
            int port = 23;
            String host = buf[0] + "." + buf[1] + "." + (buf[2]+256) + "." + i;
            try {
                Socket socket = new Socket(host, port);
                System.out.println("Connect: " + host);
            } catch (IOException e) {
                System.out.println("Could not connect on port: " + port + " " + host);
                // ...
            }
        }
    }

    public Set<String> getSensors() {
        return sensors;
    }

    public void setSensors(Set<String> sensors) {
        this.sensors = sensors;
    }

}
