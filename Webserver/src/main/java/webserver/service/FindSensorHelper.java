package webserver.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import webserver.domain.Metric;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

@Service
public class FindSensorHelper {

    //Сканирование подсети сервера для поиска сенсоров
    //Чтобы выдавать их списком при добавлении в группу

    private Set<Metric> metrics;
    private Set<String> interfaces;


    private Metric metric;

    public FindSensorHelper() {
        metrics = new HashSet<>();
    }

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
    public void scanNetwork(String ipAddress, int port) throws  UnknownHostException {
        InetAddress hostname = InetAddress.getByName(Inet4Address.getLocalHost().getHostAddress());

        if (!ipAddress.isEmpty()) {
            hostname = InetAddress.getByName(ipAddress);
        }
        byte[] buf = hostname.getAddress();

        if ((0xff & buf[3]) != 0 && (0xff & buf[3]) != 255) {
            String host = (0xff & buf[0]) + "." + (0xff & buf[1]) + "." + (0xff & buf[2]) + "." + (0xff & buf[3]);
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(host, port), 20);
                socket.close();
                System.out.println("Connect: " + host);
                metric = new Metric();
                metric.setIpAddress(host);
                metric.setTitle(host);
                metric.setPort(port);
                metrics.add(metric);
            } catch (IOException e) {
                System.out.println("Could not connect to host " + host + " on port: " + port);
            }
        } else {
            for (int i = 1; i < 255; i++) {

                String host = (0xff & buf[0]) + "." + (0xff & buf[1]) + "." + (0xff & buf[2]) + "." + i;
                try {
                    Socket socket = new Socket();
                    socket.connect(new InetSocketAddress(host, port), 20);
                    socket.close();
                    System.out.println("Connect: " + host +":"+ port);
                    metric = new Metric();
                    metric.setIpAddress(host);
                    metric.setTitle(host);
                    metric.setPort(port);
                    metrics.add(metric);
                } catch (IOException e) {
                    System.out.println("Could not connect to host " + host + " on port: " + port);
                }
            }
        }
    }



    public Set<Metric> getMetrics() {
        return metrics;
    }

    public void setMetrics(Set<Metric> metrics) {
        this.metrics = metrics;
    }

    public Set<String> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(Set<String> interfaces) {
        this.interfaces = interfaces;
    }


}
