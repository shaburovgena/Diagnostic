package webserver.util;


import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class MetricsScanner {


    public MetricsScanner() {
    }

//    @Async
//    @Scheduled(fixedRate = 6000)
    void metricsScan() {

//        System.out.println("Scanning");
    }
}
