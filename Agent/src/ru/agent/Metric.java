package ru.agent;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;

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

        JSONObject json = new JSONObject();
        json.put("time", new Date().getTime());
        json.put("label", "metric"); //Маркер для сервера что данные пришли от агента
        json.put("title", this.getClass().getSimpleName());
        json.put("value", getMetric());
        return json;
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
