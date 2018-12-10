package ru.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.json.JSONObject;
import ru.agent.DiskUsageMetric;
import ru.agent.FileValueMetric;
import ru.agent.Metric;
import ru.services.PushService;

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

//            String metricName = t.getRequestURI().getPath().replace("/", "");

            if (t.getRequestMethod().equalsIgnoreCase("POST")) {

                String[] lines = new BufferedReader(
                        new InputStreamReader(t.getRequestBody())
                ).lines().toArray(String[]::new);

                System.out.println("\n\n request");

                for (String line : lines) {

                    System.out.println(line);

                    Metric metric = null;

                    JSONObject jsonRequest = new JSONObject(line);
                    System.out.println(jsonRequest.getString("metricName"));
                    if (jsonRequest.getString("metricName") == "DiskUsageMetric"){
                        metric = new DiskUsageMetric(jsonRequest.getString("value"));
                        System.out.println(jsonRequest.getString("metricName") + " " +
                                jsonRequest.getString("value"));
                    }else if (jsonRequest.getString("metricName") == "FileValueMetric"){
                        metric = new FileValueMetric(jsonRequest.getString("value"));
                            System.out.println(jsonRequest.getString("metricName") + " " +
                                    jsonRequest.getString("value"));
                    }
//                    switch (jsonRequest.getString("metricName")) {
//                        case "DiskUsageMetric":
//                            metric = new DiskUsageMetric(jsonRequest.getString("value"));
//                            System.out.println("DiskUsageMetric");
//                            break;
//                        case "FileValueMetric":
//                            metric = new FileValueMetric(jsonRequest.getString("value"));
//                            System.out.println("FileValueMetric");
//                            break;
//                    }
//                    switch (jsonRequest.getString("label")) {
//                        case "login":
//                            //TODO Проверка пользователя
//                            break;
//                        case "register":
//                            //TODO Регистрация нового пользователя
//                            break;
//                    }
                    if (metric != null && metric.alert()) {

                        //При превышении значения отправляем пуш уведомление

                        System.out.println(metric);
                        pushService.notify(metric);
                    }
                }
            }

            String response = "ok";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.flush();

        }
    }
}
