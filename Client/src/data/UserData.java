package data;

public class UserData {
    private String login;
    private String password;
    private String name;
    private String mail;
    private String phone;
    private String id;

    public UserData() {
    }


    public UserData(String login, String password) {
        this.setLogin(login);
        this.setPassword(password);
    }

    public UserData(String login, String password, String name, String phone, String mail) {
        this.setLogin(login);
        this.setPassword(password);
        this.setName(name);
        this.setPhone(phone);
        this.setMail(mail);
    }
    public UserData(String id, String login, String password, String name, String phone, String mail) {
        this.setId(id);
        this.setLogin(login);
        this.setPassword(password);
        this.setName(name);
        this.setPhone(phone);
        this.setMail(mail);
    }

    public String getLogin() {
        return login;
    }

    public String getPassw() {
        return password;
    }
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public String getMail() {
        return mail;
    }
    public String getId() {return id;}
    private void setId(String id) {
        this.id = id;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
}
