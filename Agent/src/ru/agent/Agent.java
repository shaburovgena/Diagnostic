package ru.agent;

import java.io.IOException;

public class Agent {
    public static void main(String[] args) {
        Metric diskUsage = new DiskUsageMetric("http://localhost:9001", 10000);
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
