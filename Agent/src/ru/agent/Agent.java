package ru.agent;

import ru.services.Send;

import java.io.IOException;
//Агент для работы на удаленных ПК
// TODO: 19.02.2019 Данные должны запрашиваться с сервера
public class Agent {
    public static void main(String[] args) {
        Metric testMetric = new TestMetric(5000);
//        Metric testMetric = new FileValueMetric(2000);
        Send send = new Send();
        while (true) {
            try {
                Thread.sleep(testMetric.getInterval());
                System.out.println(testMetric.getJSONMetric());
                send.get(testMetric.getJSONMetric(), "agent");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
