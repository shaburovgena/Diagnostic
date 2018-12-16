package controllers;

import java.net.*;
import java.util.ResourceBundle;

import data.NewWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.json.JSONObject;
import ru.services.PushService;
import ru.services.Send;

/**
 * Created by Gena on 23.07.2018.
 * Стартовая страница
 */
public class StartPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane loginPanel;

    @FXML
    private Button btnSignUp;

    @FXML
    private TextField textLoginField = null;

    @FXML
    private PasswordField textPasswField = null;

    @FXML
    private Label label;
    @FXML
    private Label labelAuth;

    @FXML
    private AnchorPane panel;

    @FXML
    private Button btnSignIn;


    @FXML
    void initialize() {
        //Обработка данных на главной странице
        Send send = new Send();
        NewWindow newWindow = new NewWindow();
        JSONObject request = new JSONObject();
        btnSignIn.setOnAction(value -> {
            request.put("label", "login");
            request.put("login", textLoginField.getText());
            request.put("password", textPasswField.getText());
            send.post(request, "client");

            //TODO Добавить проверку пользователя

            int responseCode = send.getResponseCode();
            System.out.println("SignIn Page response code " + responseCode);

//            int responseCode = 200;
            if (responseCode == 401) { //Не авторизован
                btnSignIn.getScene().getWindow().hide();
                newWindow.openWindow("Unauthorized");
                System.out.println("не авторизоан");
            } else if (responseCode == 200) {//Авторизован
                btnSignIn.getScene().getWindow().hide();
                newWindow.openWindow("WelcomePage");
                System.out.println("авторизован");
            } else if (responseCode == 400) {//Не указан логин-пароль
                labelAuth.setText("Please enter login/password");
                System.out.println("не указан логин-пароль");
            }
        });
        btnSignUp.setOnAction(value -> {
            btnSignIn.getScene().getWindow().hide();
            newWindow.openWindow("SignUpPage");
        });
    }


}
