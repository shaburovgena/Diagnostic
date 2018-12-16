package ru.agent;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Metric {
    String value;
    long interval;

    public Metric() {
    }

    public Metric(int interval) {
        this.interval = interval;
    }

    public long getInterval() {
        return interval;
    }


    public boolean alert() {
        return false;
    }

    //Отправка запроса вида " {metricName":"FileValueMetric","label":"metric","value":"cmd.exe ...}"

    public JSONObject getJSONMetric() throws IOException {
//        URL obj = new URL(serverUrl);
//        HttpURLConnection con = (HttpURLConnection) obj.openConnection();


//        byte[] out = (this.getClass().getSimpleName() + " " + getMetric()).getBytes(StandardCharsets.UTF_8);

        JSONObject json = new JSONObject();
        json.put("label", "metric"); //Маркер для сервера что данные пришли от агента
        json.put("metricName", this.getClass().getSimpleName());
        json.put("value", getMetric());
        return json;
//        con.setDoOutput(true);
//
//        ByteArrayOutputStream byteStream = new
//                ByteArrayOutputStream(400);
//        PrintWriter out = new PrintWriter(byteStream, true);
//        out.write(json.toString());
//        out.flush();
//
//        con.setRequestMethod("POST");
//        con.setRequestProperty("Content-Length", String.valueOf(byteStream.size()));
//        con.setRequestProperty("Content-Type",
//                "application/json");
//        byteStream.writeTo(con.getOutputStream());
//
//        System.out.println("\nSending 'POST' request to URL : " + serverUrl);
//        System.out.println("Post parameters : " + json.toString());
//        System.out.println("Response Code : " + con.getResponseCode());
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(con.getInputStream()));
//
//        String inputLine;
//        StringBuffer response;
//        response = new StringBuffer();
//        while ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//        }
//        System.out.println(response.toString());
    }

    public String getMetric() {
        return " Returned metric";
    }

    public String getNotificationText() {
        return "Nothing";
    }

    //Выполняет команду на сервере и считывает результат

    protected String executeCommand(String command) {
        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}
