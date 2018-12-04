package ru.agent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Metric {
    String serverUrl;
    String value;
    long interval;

    public Metric() {
    }

    public Metric(String serverUrl, int interval) {
        this.interval = interval;
        this.serverUrl = serverUrl;
    }

    public long getInterval() {
        return interval;
    }


    public boolean alert() {
        return false;
    }

    public void post() throws IOException {
        URL obj = new URL(serverUrl);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("POST");
        con.setDoOutput(true);

        byte[] out = (this.getClass().getSimpleName() + " " + getMetric()).getBytes(StandardCharsets.UTF_8);

        con.setFixedLengthStreamingMode(out.length);
        con.connect();

        OutputStream os = con.getOutputStream();
        os.write(out);

        int responseCode = con.getResponseCode();
        System.out.println("Response Code " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));

        String inputLine;
        StringBuffer response;
        response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        System.out.println(response.toString());
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
