package ru.agent;

import ru.services.Send;

import java.io.IOException;
/*TODO: Универсальный агент для RPi и Arduino
Проверка подключенных сенсоров
Удаленное включение компонентов
Управление через response
**/
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
