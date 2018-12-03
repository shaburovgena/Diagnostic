package ru.services;

import ru.agent.Metric;

import java.net.URL;

public class PushService {
    static final String SERVER_KEY = "";
    static final String ID_TOKEN = "";
    static final String SERVER_URL = "http://localhost:8443";
    static final String FCM_URL = "https://fcm.googleapis.com/fcm/send";

    public void notify(Metric metric) {
        sendNotification(metric.getClass().getSimpleName(), metric.getNotificationText());

    }

     void sendNotification(String title, String body) {
         System.out.println("Notification");
         URL obj = new URL (FCM_URL);
    }
}
