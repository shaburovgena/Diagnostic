package ru.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 Десктопный клиент, функционал перенесен в веб интерфейс
 */
public class Client extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/samples/StartPage.fxml"));
        primaryStage.setTitle("Diagnostic Tool");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
