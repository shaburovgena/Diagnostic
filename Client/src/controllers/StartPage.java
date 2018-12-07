package controllers;

import java.net.*;
import java.util.ResourceBundle;

import data.Send;
import data.UserData;
import data.UserDataHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import ru.services.PushService;

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
    private TextField textLoginField=null;

    @FXML
    private PasswordField textPasswField=null;

    @FXML
    private Label label;
    @FXML
    private Label labelAuth;

    @FXML
    private AnchorPane panel;

    @FXML
    private Button btnSignIn;

    public static UserDataHandler handler;


    @FXML
    void initialize() {
//todo Обработка данных на главной странице

        Send send = new Send();
        btnSignIn.setOnAction(value ->{
            UserData user = new UserData(textLoginField.getText(), textPasswField.getText());
            handler = new UserDataHandler();
            handler.setUser(user);
            int response = send.sendPost(user, PushService.SERVER_URL);
            if (response==401){
                btnSignIn.getScene().getWindow().hide();
                send.openWindow("Unauthorized");
                System.out.println("не авторизоан");
            }else if(response==200){
                btnSignIn.getScene().getWindow().hide();
                send.openWindow("WelcomePage");
            }else if(response==400){
                labelAuth.setText("Please enter login/password");
            }
            });
        btnSignUp.setOnAction(value -> {
            btnSignIn.getScene().getWindow().hide();
            send.openWindow("SignUpPage");
        });
    }

//    public void openWindow (String window) {
//
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("/samples/"+window+".fxml"));
//        try {
//            loader.load();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Parent root = loader.getRoot();
//        Stage stage = new Stage();
//        stage.setScene(new Scene(root));
//        stage.show();
//    }
//
//    public int sendPost(UserData user){
//        String login = user.getLogin();
//        String password = user.getPassw();
//        String query = "login="+login;
//        query += "&password="+password;
//
//        StringBuffer response = null;
//        int responseCode = 0;
//        try {
//            String myURL = "http://127.0.0.1:8443/signin";
//            URL url = new URL(myURL);
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();// создание класса URLCONNECTION
//            con.setDoOutput(true);
//
//            ByteArrayOutputStream byteStream = new
//                    ByteArrayOutputStream(400);
//            PrintWriter out = new PrintWriter(byteStream, true);
////            String post = "login="+textLoginField.getText() + "&password=" + textPasswField.getText();
//            out.write(query);
//            out.flush();
//
//            //add reuqest header
//            con.setRequestMethod("POST");
//            con.setRequestProperty("Content-Length", String.valueOf(byteStream.size()));
//            con.setRequestProperty("Content-Type",
//                    "application/x-www-form-urlencoded");
//            byteStream.writeTo(con.getOutputStream());
//            // Send post request
//
//            responseCode = con.getResponseCode();
//            System.out.println("\nSending 'POST' request to URL : " + url);
//            System.out.println("Post parameters : " + query);
//            System.out.println("Response Code : " + responseCode);
//            BufferedReader in = null;
//            in = new BufferedReader(
//                    new InputStreamReader(con.getInputStream()));
//            String inputLine;
//            response = new StringBuffer();
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            System.out.println(response.toString());
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return responseCode;
//    }
}
