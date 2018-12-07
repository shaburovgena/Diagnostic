package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import data.Send;
import data.UserData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ChData {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCancel;

    @FXML
    private AnchorPane loginPanel;

    @FXML
    private TextField newNameField = null;

    @FXML
    private Label ChPswLabel;

    @FXML
    private Button btnCh;

    @FXML
    private TextField newPhoneField = null;

    @FXML
    private Label label;

    @FXML
    private AnchorPane panel;

    @FXML
    private TextField newMailField;

    @FXML
    void ffa641(ActionEvent event) {

    }

    @FXML
    void initialize() {
        UserData user = StartPage.handler.getUser();
        Send send = new Send();
        btnCancel.setOnAction(value ->{
            btnCancel.getScene().getWindow().hide();
            send.openWindow("WelcomePage");
        });
        btnCh.setOnAction(value -> {
            String login = user.getLogin();
            String password = user.getPassw();
            String name = newNameField.getText();
            String phone = newPhoneField.getText();
            String mail = newMailField.getText();
            UserData userUpdate = new UserData(login, password, name, phone, mail);
                    int resp = send.sendPost(userUpdate, "update");
                    btnCh.getScene().getWindow().hide();
                    send.openWindow("WelcomePage");
        });

    }
}
