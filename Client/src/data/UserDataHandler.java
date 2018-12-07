package data;

public class UserDataHandler {
   private static UserData user;

    public UserDataHandler(){

    }

    public void setUser(UserData user){
        this.user = user;
    }

    public static UserData getUser () {
        return user;
    }
}
