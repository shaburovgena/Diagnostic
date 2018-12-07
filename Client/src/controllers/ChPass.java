package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import data.Send;
import data.UserData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;

public class ChPass {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField confPsw;

    @FXML
    private AnchorPane loginPanel;

    @FXML
    private PasswordField oldPsw;

    @FXML
    private Label label;

    @FXML
    private AnchorPane panel;

    @FXML
    private Button btnCh;
    @FXML
    private Button btnCancel;
    @FXML
    private PasswordField newPsw;
    @FXML
    private Label ChPswLabel;
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
            System.out.println(login);
            String password = user.getPassw();
            String oldPassword = oldPsw.getText();
            String newPassword = newPsw.getText();
            String confirmPassword = confPsw.getText();
            System.out.println(password);
            int response =send.sendPost(user, "signin");
            if(oldPassword.equals(password)&&response==200){
                if(newPassword.equals(confirmPassword)) {
                    UserData userUpdate = new UserData(login, newPassword);
                    int resp = send.sendPost(userUpdate, "updatepsw");
                    btnCh.getScene().getWindow().hide();
                    send.openWindow("WelcomePage");
                  System.out.println(resp);
              }else{
                  ChPswLabel.setText("Error confirm password");
              }
            }else{
                ChPswLabel.setText("Wrong old password");
            }

        });

    }
}
