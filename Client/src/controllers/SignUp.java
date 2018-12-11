package controllers;

import data.Send;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.json.JSONObject;

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

        //Регистрация нового пользователя

        Send send = new Send();
        JSONObject request = new JSONObject();
        btnSignUp.setOnAction(value -> {

            request.put("label", "register");
            request.put("login", textLoginField.getText());
            request.put("password", textPasswField.getText());
            request.put("mail", textMailField.getText());
            request.put("phone", textPhoneField.getText());
            request.put("name", textNameField.getText());
            send.post(request);
//            int responseCode = send.getResponseCode();
            int responseCode = 200;
            if (responseCode == 409) {//Пользователь существует
                labelReg.setText("User exist");
            } else if (responseCode == 200) {//Успешная регистрация
                btnSignUp.getScene().getWindow().hide();
                send.openWindow("StartPage");
            } else if (responseCode == 400) {//Ошибка ввода логин-пароля
                labelReg.setText("Wrong login/password");
            }
        });
        btnSignIn.setOnAction(value -> {
            btnSignIn.getScene().getWindow().hide();
            send.openWindow("StartPage");
        });
    }

}
