package data;

import data.UserData;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * Created by Gena on 23.07.2018.
 * Обработка запрос/ответ
 * Открытие новой сцены
 */
public class Send {
    public Send(){}

    public void openWindow (String window) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/samples/" +window+".fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public int sendPost(UserData user, String serverURL){
        String login = user.getLogin();
        String password = user.getPassw();
        String name = user.getName();
        String phone = user.getPhone();
        String mail = user.getMail();

        JSONObject json = new JSONObject();
        json.put("login", login);
        json.put("password", password);
        json.put("name", name);
        json.put("phone", phone);
        json.put("mail", mail);
        StringBuffer response = null;
        int responseCode = 0;
        try {
            URL url = new URL(serverURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();// создание класса URLCONNECTION
            con.setDoOutput(true);

            ByteArrayOutputStream byteStream = new
                    ByteArrayOutputStream(400);
            PrintWriter out = new PrintWriter(byteStream, true);
            out.write(json.toString());
            out.flush();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Length", String.valueOf(byteStream.size()));
            con.setRequestProperty("Content-Type",
                    "application/json");
            byteStream.writeTo(con.getOutputStream());
            // Send post request

            responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + json.toString());
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = null;
            in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            System.out.println(response.toString());
        } catch (Exception e){
            e.printStackTrace();
        }

        return responseCode;
    }
    public String sendPost(String serverURL){

        StringBuffer response = null;
        int responseCode = 0;
        try {
            String myURL = "http://127.0.0.1:8443/"+serverURL;
            URL url = new URL(myURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();// создание класса URLCONNECTION
            con.setDoOutput(true);

            ByteArrayOutputStream byteStream = new
                    ByteArrayOutputStream(400);
            PrintWriter out = new PrintWriter(byteStream, true);
            out.write("");
            out.flush();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Length", String.valueOf(byteStream.size()));
            con.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            byteStream.writeTo(con.getOutputStream());
            // Send post request

            responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + "view");
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = null;
            in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            System.out.println(response.toString());
        } catch (Exception e){
            e.printStackTrace();
        }

        return response.toString();
    }
}
