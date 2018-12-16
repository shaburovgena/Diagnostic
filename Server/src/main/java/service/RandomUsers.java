package service;

import db.DBException;
import db.DBService;
import org.json.JSONArray;
import org.json.JSONObject;
import ru.services.Send;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class RandomUsers {
    //получение списка пользователей с сайта randomuser.me
    private final DBService dbService;
    StringBuffer response = null;
    String result = "";
    String urlRandomUser = "https://randomuser.me/api/?results=500";
    Send send = new Send();

    //https://randomuser.me/api/?results=50
    public RandomUsers(DBService dbService) {
        this.dbService = dbService;
    }

    public void getRandomUserData() {
        send.post(urlRandomUser);
        result = send.getResponse();
        JSONArray jsonArray = (JSONArray) new JSONObject(result).get("results");
//        addUserAuto(jsonArray);
    }

    public void addUserAuto(JSONArray jsonArray) {

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            JSONObject str = (JSONObject) jsonObject.get("name");
            JSONObject str1 = (JSONObject) jsonObject.get("login");

            String name = str.getString("first") + "  " + str.getString("last");
            String login = str1.getString("username");
            String password = str1.getString("password");
            String mail = jsonObject.getString("email");
            String phone = jsonObject.getString("phone");
            try {
                dbService.addUser(login, password, name, phone, mail);
            } catch (DBException e) {
                e.printStackTrace();
            }
        }
    }
}
