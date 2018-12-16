package ru.agent;

import ru.services.PushService;
import ru.services.Send;

import java.io.IOException;

public class Agent {
    public static void main(String[] args) {
        Metric testMetric = new TestMetric(5000);
        Send send = new Send();
        while (true) {
            try {
                Thread.sleep(testMetric.getInterval());
                send.post(testMetric.getJSONMetric(), "agent");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
