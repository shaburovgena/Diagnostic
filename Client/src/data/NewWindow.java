package data;

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
public class NewWindow {


    public NewWindow() {

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







}