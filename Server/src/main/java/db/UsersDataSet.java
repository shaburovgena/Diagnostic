package db;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Gena on 16.07.2018.
 */
@Entity
@Table(name = "users")
public class UsersDataSet implements Serializable {
    private static final long serialVersionUID = -8706689714326132798L;

    @Id
    @Column(name = "id", unique = true, updatable = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "login", unique = true, updatable = true, insertable = true)
    private String login;
    @Column(name = "password", unique = false, updatable = true, insertable = true)
    private String password;
    @Column(name = "name", unique = false, updatable = true, insertable = true)
    private String name;
    @Column(name = "phone", unique = false, updatable = true, insertable = true)
    private String phone;
    @Column(name = "mail", unique = false, updatable = true, insertable = true)
    private String mail;


    public UsersDataSet() {
    }


    public UsersDataSet(String login, String password) {
//        this.setId(id);
        this.setLogin(login);
        this.setPassword(password);


    }

    public UsersDataSet(String login, String password, String name, String phone, String mail) {
        this.setId(-1);
        this.setLogin(login);
        this.setPassword(password);
        this.setName(name);
        this.setPhone(phone);
        this.setMail(mail);
    }

    public UsersDataSet(int id, String login, String password, String name, String phone, String mail) {
        this.setId(id);
        this.setLogin(login);
        this.setPassword(password);
        this.setName(name);
        this.setPhone(phone);
        this.setMail(mail);
    }


    public String getName() {
        return name;
    }
    public String getLogin() {
        return login;
    }
    public String getPhone() {
        return phone;
    }
    public String getMail() {
        return mail;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }
}