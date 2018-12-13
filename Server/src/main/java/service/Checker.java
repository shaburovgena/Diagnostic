package service;

import db.Const;
import db.Executor;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;

public class Checker {
    private static Executor executor;

    public Checker(Executor executor) {
        this.executor = executor;
    }

    public boolean ifExist(JSONObject jsonObject) throws IOException, SQLException {
        String login = jsonObject.getString("login");
        String password = jsonObject.getString("password");
        String query = "SELECT login,password FROM " + Const.USER_TABLE + " WHERE login = '" + login + "' AND password = '" + password + "';";
//        String query = "SELECT * FROM users";
        System.out.println(query);
        boolean value = executor.execQuery(query, resultSet -> {
            boolean val = false;
            while (resultSet.next()) {
                if (resultSet.getString("login").equals(jsonObject.getString("login")) &&
                        resultSet.getString("password").equals(jsonObject.getString("password"))) {
                    val = true;
                    System.out.println(val);
                    break;
                } else {
                    val = false;
                    System.out.println(val);
                    break;
                }
            }
            return val;
        });
        return value;
    }
}
