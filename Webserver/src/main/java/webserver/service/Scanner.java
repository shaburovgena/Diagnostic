package webserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import webserver.domain.Metric;
import webserver.repos.MetricRepo;

import java.io.IOException;
import java.net.*;
import java.time.LocalTime;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

@Service
public class Scanner {

    //Сканирование подсети сервера для поиска сенсоров
    //Чтобы выдавать их списком при добавлении в группу
    @Autowired
    private MetricRepo metricRepo;
    private Set<Metric> metrics;
    private Set<String> interfaces;


    private Metric metric;

    public Scanner() {
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
    public void scan(String ipAddress, int port) throws SocketException, UnknownHostException {
        InetAddress hostname = InetAddress.getByName(Inet4Address.getLocalHost().getHostAddress());

        if(!ipAddress.isEmpty()) {
            hostname = InetAddress.getByName(ipAddress);
        }
        byte[] buf = hostname.getAddress();

        if((0xff & buf[3]) !=0 && (0xff & buf[3])!=255){
            String host = (0xff & buf[0]) + "." + (0xff & buf[1]) + "." + (0xff & buf[2]) + "." + (0xff & buf[3]);
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(host, port), 20);
                socket.close();
                System.out.println("Connect: " + host);
                metric = new Metric(host, String.valueOf(InetAddress.getByName(host)));
                metrics.add(metric);
            } catch (IOException e) {
                System.out.println("Could not connect to host " + host + " on port: " + port);
            }
        }else {
            for (int i = 1; i < 255; i++) {

                String host = (0xff & buf[0]) + "." + (0xff & buf[1]) + "." + (0xff & buf[2]) + "." + i;
                try {
                    Socket socket = new Socket();
                    socket.connect(new InetSocketAddress(host, port), 20);
                    socket.close();
                    System.out.println("Connect: " + host);
                    metric = new Metric(host, String.valueOf(InetAddress.getByName(host)));
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
