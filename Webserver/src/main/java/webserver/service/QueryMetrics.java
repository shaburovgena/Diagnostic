package webserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import webserver.domain.Metric;
import webserver.repos.MetricRepo;

import java.util.Collections;
import java.util.Iterator;

@Service
public class QueryMetrics {
    @Autowired
    private MetricRepo metricRepo;
    @Autowired
    private RestTemplate restTemplate;

    public QueryMetrics() {

    }

    @Async
    public void sendRequest(Iterable<Metric> metrics) {
        Iterator<Metric> metricsIterator = metrics.iterator();
        while (metricsIterator.hasNext()) {
            Metric metric = metricsIterator.next();
            System.out.println("Sending request to IP:" + metric.getIpAddress() + ":" + metric.getPort());
            String url = String.format("http://" + metric.getIpAddress() + ":" + metric.getPort());
            try {
                Metric response = restTemplate.postForObject(url, Collections.emptyList(), Metric.class);
                metric.setValue(response.getValue());
                metricRepo.save(metric);
            } catch (ResourceAccessException e) {
                e.printStackTrace();
            } catch (HttpClientErrorException e) {
                e.printStackTrace();
            }
        }
    }
}
