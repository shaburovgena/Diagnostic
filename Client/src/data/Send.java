package data;

import data.UserData;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONObject;
import ru.services.PushService;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Gena on 23.07.2018.
 * Обработка запрос/ответ
 * Открытие новой сцены
 */
public class Send {

    private int responseCode;
    private String serverURL;

    public Send() {
        serverURL = PushService.SERVER_URL + "/client";
    }

    public void openWindow(String window) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/samples/" + window + ".fxml"));
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



    public int getResponseCode() {
        return responseCode;
    }

    public String post(JSONObject request) {

        StringBuffer response = null;

        try {
            URL url = new URL(serverURL);
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
            response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            System.out.println(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return String.valueOf(response);
    }


}