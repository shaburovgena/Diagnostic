package controllers;

import data.Send;
import data.UserData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONArray;
import org.json.JSONObject;

public class View {

    @FXML
    private TableColumn<UserData, String> nameColumn;

    @FXML
    private TableColumn<UserData, String> loginColumn;

    @FXML
    private TableColumn<UserData, String> pswColumn;

    @FXML
    private TableColumn<UserData, String> phoneColumn;

    @FXML
    private TableColumn<UserData, String> mailColumn;

    @FXML
    private TableColumn<UserData, String> idColumn;
    @FXML
    private Button btnCancel;
    @FXML
    private TableView<UserData> table;


    @FXML
    private Label label;
    private ObservableList<UserData> usersList;

    @FXML
    void initialize() {
        assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'ViewPage.fxml'.";
        assert label != null : "fx:id=\"label\" was not injected: check your FXML file 'ViewPage.fxml'.";
        Send send = new Send();
        usersList = FXCollections.observableArrayList();
        String response = send.sendPost("view");
        JSONArray jsonArray = new JSONArray(response);
        idColumn.setCellValueFactory(new PropertyValueFactory<UserData, String>("id"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<UserData, String>("login"));
        pswColumn.setCellValueFactory(new PropertyValueFactory<UserData, String>("password"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<UserData, String>("name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<UserData, String>("phone"));
        mailColumn.setCellValueFactory(new PropertyValueFactory<UserData, String>("mail"));

        for (int i  = 0; i < jsonArray.length(); i++){
            JSONObject json = (JSONObject) jsonArray.get(i);
            String id = json.getString("id");
            String login = json.getString("login");
            String password = json.getString("password");
            String name = json.getString("name");
            String phone = json.getString("phone");
            String mail = json.getString("mail");
            UserData userData = new UserData(id, login, password, name, phone, mail);

            usersList.add(userData);
        }
        table.setItems(usersList);

        btnCancel.setOnAction(value ->{
            btnCancel.getScene().getWindow().hide();
            send.openWindow("WelcomePage");
        });
    }
}
