package ru.services;


import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Gena on 23.07.2018.
 * Обработка запрос/ответ
 * Открытие новой сцены
 */

//Часть сервиса для работы клиента и агента, устарел
public class Send {

    private int responseCode;
    private String response;

    public Send() {

    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponse() {
        return response;
    }

    public void get (JSONObject request, String serverURL){

        serverURL  = PushService.SERVER_URL + serverURL + "?time=" + request.getLong("time") +
                "&title=" + request.getString("title") +
                "&value=" + request.getString("value");

        try {
            URL  url = new URL(serverURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void get(String serverURL) {
        StringBuffer sb = null;
        try {
            URL url = new URL(serverURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            int responseCode = con.getResponseCode();

            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);
            BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
            sb = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        response = String.valueOf(sb);

    }

    public void post(JSONObject request) {
        this.post(request, PushService.SERVER_URL);
    }

    public void post(JSONObject request, String serverURL) {
        String URL = PushService.SERVER_URL + serverURL;
        StringBuffer sb = null;

        try {
            URL url = new URL(URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);

            ByteArrayOutputStream byteStream = new
                    ByteArrayOutputStream(400);
            PrintWriter out = new PrintWriter(byteStream);
            out.write(request.toString());
            out.flush();

            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Length",
                    String.valueOf(byteStream.size()));
            con.setRequestProperty("Content-Type",
                    "application/json");
            byteStream.writeTo(con.getOutputStream());

            this.responseCode = con.getResponseCode();

            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + request);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = null;
            in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            sb = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                sb.append(inputLine);
            }
            System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        response = String.valueOf(sb);

    }


}