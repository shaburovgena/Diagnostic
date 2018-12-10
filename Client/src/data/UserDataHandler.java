package data;

import org.json.JSONObject;

public class UserDataHandler {
    private static UserData user;

    public UserDataHandler() {

    }

    public void setUser(UserData user) {
        this.user = user;
    }

    public JSONObject toJsonFormat (UserData user) {
        String login = user.getLogin();
        String password = user.getPassw();
        String name = user.getName();
        String phone = user.getPhone();
        String mail = user.getMail();

        JSONObject json = new JSONObject();
        json.put("login", login);
        json.put("password", password);
        json.put("name", name);
        json.put("phone", phone);
        json.put("mail", mail);

        return json;
    }

    public static UserData getUser() {
        return user;
    }
}
