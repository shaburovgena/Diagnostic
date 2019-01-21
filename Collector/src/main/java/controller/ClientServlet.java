package controller;

import db.DBException;
import db.DBService;
import org.json.JSONException;
import org.json.JSONObject;
import service.Checker;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Gena on 17.12.2018.
 * Сервлет обработки запросов от клиентов
 */
public class ClientServlet extends HttpServlet {

    private final Checker checker;
    private final DBService dbService;
    private JSONObject jsonRequest;

    public ClientServlet(Checker checker, DBService dbService) {
        this.checker = checker;
        this.dbService = dbService;
    }


    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        long oldTime = new Date().getTime();
        jsonRequest = null;
        StringBuffer sb = new StringBuffer();
        String line = null;
        //Получаем запрос в формате JSON
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            jsonRequest = new JSONObject(sb.toString());
            String label = jsonRequest.getString("label");
            //Проверка указания логина и пароля
            if (!jsonRequest.getString("login").equals("") ||
                    !jsonRequest.getString("password").equals("")) {

                //Проверяем метки (вход или регистрация)
                if (label.equals("login")) {
                    //Проверяем есть ли пользователь в базе
                    if (checker.ifExist(jsonRequest)) {
                        System.out.println("ClientServlet login apply " + (new Date().getTime() - oldTime));
                        response.setContentType("text/html;charset=utf-8");
                        response.setStatus(HttpServletResponse.SC_OK); //200
                    } else {
                        System.out.println("ClientServlet login denied " + (new Date().getTime() - oldTime));
                        response.setContentType("text/html;charset=utf-8");
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //401
                    }
                }
                //Метка регистрации в запросе
                if (label.equals("register")) {
                    //Если не найден в базе, создаем
                    if (!checker.ifExist(jsonRequest)) {
                        long id = dbService.addUser(jsonRequest.getString("login"),
                                jsonRequest.getString("password"),
                                jsonRequest.getString("name"),
                                jsonRequest.getString("phone"),
                                jsonRequest.getString("mail")
                        );
                        System.out.println("ClientServlet register apply");
                        response.setContentType("text/html;charset=utf-8");
                        response.setStatus(HttpServletResponse.SC_OK); //200
                    } else {
                        System.out.println("ClientServlet register failed");
                        response.setContentType("text/html;charset=utf-8");
                        response.setStatus(HttpServletResponse.SC_CONFLICT); //409
                    }

                }
            } else {
                System.out.println("Login or password field is empty");
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST); //400
            }

        } catch (JSONException e) {
            throw new IOException("Error parsing JSON request string");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DBException e) {
            e.printStackTrace();
        }

    }

}