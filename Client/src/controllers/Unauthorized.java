package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import data.Send;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class Unauthorized {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane loginPanel;

    @FXML
    private Button btnSignUp;

    @FXML
    private Label label;

    @FXML
    private AnchorPane panel;

    @FXML
    private Button btnSignIn;




    @FXML
    void initialize() {
        //Информационное окно об отсутствие учетных данных
        Send send = new Send ();
        btnSignIn.setOnAction(value ->{
            btnSignIn.getScene().getWindow().hide();
            send.openWindow("StartPage");
        });
        btnSignUp.setOnAction(value ->{
            btnSignIn.getScene().getWindow().hide();
            send.openWindow("SignUpPage");
        });
    }

}


