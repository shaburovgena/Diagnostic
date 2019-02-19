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
        InetAddress localhost = InetAddress.getByName(Inet4Address.getLocalHost().getHostAddress());
        byte[] buf = localhost.getAddress();
        metric = new Metric();
        for (int i = 1; i < 255; i++) {

            String host = (0xff & buf[0]) + "." + (0xff & buf[1]) + "." + (0xff & buf[2]) + "." + i;
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(host, port), 200);
                socket.close();
                System.out.println("Connect: " + host);

                metric.setIpAddress(host);
                metric.setGroupMetric(null);
                metric.setTime(String.valueOf(LocalTime.now()));
                metric.setTitle("metric" + i);
                metric.setValue(String.valueOf(i));
                metricRepo.save(metric);
            } catch (IOException e) {
                System.out.println("Could not connect on port: " + port + " " + host);
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
