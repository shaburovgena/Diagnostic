package ru.agent;

import ru.services.PushService;

import java.io.IOException;

public class Agent {
    public static void main(String[] args) {
        Metric testMetric = new TestMetric(PushService.SERVER_URL + "/agent", 5000);

        while (true){
            try {
                Thread.sleep(testMetric.getInterval());
                testMetric.post();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
