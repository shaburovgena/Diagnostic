package ru.services;

import ru.agent.Metric;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
//Отправка пуш уведомлений с использованием firebase от google
public class PushService {
    static final String SERVER_KEY = "AAAAwdY-Xhg:APA91bGxi6uBHz9FeylHx4ShyeFPpjTcBUbOWLVhmey6VG3gTnGxWBAncIfP892pqjN1PXumOyQO4hKPWnsjstEnHhRqj7NRo-aw6rZDfmDSo65kVYJN3hiZNafuVllV35LOu8QnDstx";//TODO: get firebase google server  key
    static final String ID_TOKEN = "3vfoqFGAwfeSO5ZskUEyMYXOHss1";  //TODO: get token
    public static final String SERVER_URL = "http://localhost:8080/";
    static final String FCM_URL = "https://fcm.googleapis.com/fcm/send";

    public void notify(Metric metric) throws IOException {
        sendNotification(metric.getClass().getSimpleName(), metric.getNotificationText());
    }

    void sendNotification(String title, String body) throws IOException {
        System.out.println("Notification");
        URL obj = new URL(FCM_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //Добавляем заголовок запроса

        con.setRequestMethod("POST");
        con.setRequestProperty("Authorization", "key=" + SERVER_KEY);
        con.setRequestProperty("Content-Type", "application/json");

        //Отправка пост запроса
        con.setDoOutput(true);

        //Создание запроса

        byte[] out = ("{\n" +
                " \"notification\": {\n" +
                " \"title\": \"" + title + "\",\n" +
                " \"body\": \"" + body + "\",\n" +
                " \"icon\": \"firebase-logo.png\", \n" +
                " \"click_action\": \"" + SERVER_URL + "\"\n" +
                " ),\n" +
                " \"to\": \"" + ID_TOKEN + "\"\n" +
                ")").getBytes(StandardCharsets.UTF_8);
        int length = out.length;

        con.setFixedLengthStreamingMode(length);
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        con.connect();
        try (OutputStream os = con.getOutputStream()) {
            os.write(out);
        }
        int responseCode = con.getResponseCode();
        System.out.println("Response Code " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        //Получение результата
        System.out.println(response.toString());

    }
}
