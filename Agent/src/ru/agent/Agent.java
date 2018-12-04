package ru.agent;

import java.io.IOException;

public class Agent {
    public static void main(String[] args) {
        Metric diskUsage = new FileValueMetric("http://localhost:8443", 5000);
        while (true){
            try {
                Thread.sleep(diskUsage.getInterval());
                diskUsage.post();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
