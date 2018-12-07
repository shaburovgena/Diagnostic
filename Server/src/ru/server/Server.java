package ru.server;

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

            if (t.getRequestMethod().equalsIgnoreCase("POST")) {
                String[] lines = new BufferedReader(
                        new InputStreamReader(t.getRequestBody())
                ).lines().toArray(String[]::new);
                System.out.println("\n\n request");
                for (String line : lines) {
                    System.out.println(line);
                    String[] parts = line.split(" ");
                    Metric metric = null;
                    switch (parts[0]) {
                        case "DiskUsageMetric":
                            metric = new DiskUsageMetric(parts[1]);
                            break;
                        case "FileValueMetric":
                            metric = new FileValueMetric(parts[1]);
                            break;

                    }
                    if (metric != null && metric.alert()) {
                        System.out.println(metric);
                        pushService.notify(metric);
                    }

                    if (parts[1] !="admin"||parts[0] !="admin"){
                        String response = "falure";
                        t.sendResponseHeaders(401, response.length());
                        OutputStream os = t.getResponseBody();
                        os.write(response.getBytes());
                        os.flush();
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
