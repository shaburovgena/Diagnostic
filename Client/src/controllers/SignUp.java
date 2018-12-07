package controllers;

import data.Send;
import data.UserData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUp {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private AnchorPane loginPanel;
    @FXML
    private TextField textPhoneField;
    @FXML
    private Button btnSignUp;
    @FXML
    private TextField textLoginField;
    @FXML
    private PasswordField textPasswField;
    @FXML
    private TextField textNameField;
    @FXML
    private TextField textMailField;
    @FXML
    private Label label;
    @FXML
    private Label labelReg;
    @FXML
    private AnchorPane panel;
    @FXML
    private Button btnSignIn;

    @FXML
    void initialize() {
//todo Обработка данных при регистрации
        Send send = new Send();
            btnSignUp.setOnAction(value ->{
                UserData user = new UserData(textLoginField.getText(), textPasswField.getText(), textNameField.getText(),
                        textPhoneField.getText(), textMailField.getText());
                int response = send.sendPost(user, "add");
               //todo RESPONSE CODE ON SERVER
                if (response==409){
                    labelReg.setText("User exist");
                }else if(response==200){
                    btnSignUp.getScene().getWindow().hide();
                    send.openWindow("StartPage");
                }else if(response==400){
                    labelReg.setText("Wrong login/password");
                }
            });
            btnSignIn.setOnAction(value -> {
                btnSignIn.getScene().getWindow().hide();
                send.openWindow("StartPage");
            });
        }

}
