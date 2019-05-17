package webserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import webserver.domain.Metric;
import webserver.domain.Sensor;
import webserver.repos.MetricRepo;
import webserver.repos.SensorRepo;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Iterator;

@Service
public class QueryMetrics {
    @Autowired
    private MetricRepo metricRepo;
    @Autowired
    private SensorRepo sensorRepo;
    @Autowired
    private RestTemplate restTemplate;

    public QueryMetrics() {

    }

    @Async
    public void sendRequest(Iterable<Sensor> sensors) {
        Iterator<Sensor> sensorIterator = sensors.iterator();
        while (sensorIterator.hasNext()) {
            Sensor sensor = sensorIterator.next();
            String url = "";
            System.out.println( url = String.format("http://" + sensor.getIpAddress() + ":" + sensor.getPort()));
            try {
                Sensor response = restTemplate.postForObject(url, Collections.emptyList(), Sensor.class);
                sensor.setValue(response.getValue());
                metricRepo.save(new Metric(response.getValue(), sensor, LocalDateTime.now()));
                sensorRepo.save(sensor);
            } catch (ResourceAccessException e) {
                e.printStackTrace();
            } catch (HttpClientErrorException e) {
                e.printStackTrace();
            }
        }
    }
}
