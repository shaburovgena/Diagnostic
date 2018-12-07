package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import data.Send;
import data.UserData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
/**
 * Created by Gena on 22.07.2018.
 * Окно приветсвия + основные функции (просмотр/обновление данных/выход)
 */
public class WelcomePage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane loginPanel;

    @FXML
    private Button btnSignOut;

    @FXML
    private Button btnView;

    @FXML
    private Label label;

    @FXML
    private AnchorPane panel;

    @FXML
    private Button btnChData;

    @FXML
    private Button btnChPass;

    @FXML
    private Button btnRem;

    @FXML
    void ffa641(ActionEvent event) {
    }

    @FXML
    void initialize() {
        Send send = new Send();
        UserData user = StartPage.handler.getUser();
        btnChPass.setOnAction(value ->{
            btnChPass.getScene().getWindow().hide();
            send.openWindow("ChPassPage");
        });
        btnRem.setOnAction(value ->{
            btnRem.getScene().getWindow().hide();
            send.sendPost(user, "remove");
            send.openWindow("StartPage");
        });
        btnSignOut.setOnAction(value ->{
                btnSignOut.getScene().getWindow().hide();
                send.openWindow("StartPage");
        });
        btnChData.setOnAction(value ->{
            btnChData.getScene().getWindow().hide();
            send.openWindow("ChDataPage");
        });
        btnView.setOnAction(value ->{
            btnView.getScene().getWindow().hide();
            send.openWindow("ViewPage");
        });
    }

}
