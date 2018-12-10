package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import data.Send;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import org.json.JSONObject;

public class WelcomePage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane loginPanel;

    @FXML
    private Button btnRefresh;

    @FXML
    private Button btnSignOut;

    @FXML
    private TextArea txtView;

    @FXML
    private Label label;

    @FXML
    private AnchorPane panel;

    @FXML
    void initialize() {
    Send send = new Send();
        JSONObject request = new JSONObject();
        btnSignOut.setOnAction(value -> {
            btnSignOut.getScene().getWindow().hide();
            send.openWindow("StartPage");
        });

        btnRefresh.setOnAction(value -> {
            request.put("label", "view");
            String response = send.post(request);
            txtView.setText( response);
        });

    }
}
