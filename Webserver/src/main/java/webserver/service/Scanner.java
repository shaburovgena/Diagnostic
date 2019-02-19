package webserver.service;

import org.springframework.scheduling.annotation.Async;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

public class Scanner {

    //Сканирование подсети сервера для поиска сенсоров
    //Чтобы выдавать их списком при добавлении в группу

    private Set<String> sensors;
    private Set<String> interfaces;

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

    @Async
    public void scan() throws SocketException, UnknownHostException {

        InetAddress localhost = InetAddress.getByName(Inet4Address.getLocalHost().getHostAddress());
        byte[] buf = localhost.getAddress();

        for (int i = 254; i > 0; i--) {
            int port = 445;
            String host = (0xff & buf[0]) + "." + (0xff & buf[1]) + "." + (0xff & buf[2]) + "." + i;
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(host, port), 200);

                socket.close();
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
