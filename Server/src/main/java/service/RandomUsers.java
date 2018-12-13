package service;

import org.json.JSONArray;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class RandomUsers {
    // TODO: 13.12.2018 Добавить получение списка пользователей с сайта randomuser.me

    //https://randomuser.me/api/?results=50
    public RandomUsers() {
        StringBuffer response = null;
String result = "";
        try {
            URL url = new URL("https://randomuser.me/api/?results=50");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();


            int responseCode = con.getResponseCode();

            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);
            BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null)
            {
                sb.append(line);
            }
            rd.close();
            result = sb.toString();
            System.out.println(result);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        JSONArray jsonArray = new JSONArray(result);

    }


}
