package ru.server;

import org.json.JSONObject;
import ru.agent.DiskUsageMetric;
import ru.agent.FileValueMetric;
import ru.agent.Metric;
import ru.services.PushService;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Server {
    public static void main(String[] args) {
        int port = 8443;
        System.out.println("Server started at port " + port);

        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
            PushService pushService = new PushService();
            server.createContext("/", new MyHandler(pushService));
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class MyHandler implements HttpHandler {
        PushService pushService;

        public MyHandler(PushService pushService) {
            this.pushService = pushService;
        }

        @Override
        public void handle(HttpExchange t) throws IOException {
            String metricName = t.getRequestURI().getPath().replace("/", "");

            //Получаем пост запрос от агента или клиента

            Metric metric = null;
            StringBuffer jb = new StringBuffer();
            String line = null;
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(t.getRequestBody()));
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
            JSONObject jsonRequest = new JSONObject(jb.toString());
            System.out.println(jb);

            switch (jsonRequest.getString("metricName")) {
                case "DiskUsageMetric":
                    metric = new DiskUsageMetric(jsonRequest.getString("value"));
                    break;
                case "FileValueMetric":
                    metric = new FileValueMetric(jsonRequest.getString("value"));
                    break;
            }
            switch (jsonRequest.getString("label")) {
                case "login":
                    //TODO Проверка пользователя
                    String response = "ok";
                    t.sendResponseHeaders(200, response.length());
                    OutputStream os = t.getResponseBody();
                    os.write(response.getBytes());
                    os.flush();
                    break;
                case "register":
                    //TODO Регистрация нового пользователя
                    break;
            }
            if (metric != null && metric.alert()) {

                //При превышении значения отправляем пуш уведомление

                System.out.println(metric);
                pushService.notify(metric);
            }

            String response = "ok";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.flush();

//            if (t.getRequestMethod().equalsIgnoreCase("POST")) {
//                String[] lines = new BufferedReader(
//                        new InputStreamReader(t.getRequestBody())
//                ).lines().toArray(String[]::new);
//                System.out.println("\n\n request");
//                for (String line : lines) {
//                    System.out.println(line);
//
//                    //Разделяем строки используя разделитель пробел
//
//                    String[] parts = line.split(" ");
//                    Metric metric = null;
//                    switch (parts[0]) {
//
//                        //Если 1 часть совпадает с именем сенсора, создаем метрику со значением во 2 части
//
//                        case "DiskUsageMetric":
//                            metric = new DiskUsageMetric(parts[1]);
//                            break;
//                        case "FileValueMetric":
//                            metric = new FileValueMetric(parts[1]);
//                            break;
//
//                    }
//                    if (metric != null && metric.alert()) {
//
//                        //При превышении значения отправляем пуш уведомление
//
//                        System.out.println(metric);
//                        pushService.notify(metric);
//                    }
//                }
//            }

        }
    }
}
