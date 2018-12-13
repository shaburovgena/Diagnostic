package servlet;

import db.DBService;
import db.Executor;
import org.json.JSONException;
import org.json.JSONObject;
import service.Checker;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

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
        // TODO: 11.12.2018 обработка данных с клиента
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
            if (!jsonRequest.getString("login").equals("")||!jsonRequest.getString("password").equals("") ){

                //Проверяем метки (вход или регистрация)
                if (label.equals("login")) {
                    //TODO проверка логин пароля
                    //Проверяем есть ли пользователь в базе
                    if (checker.ifExist(jsonRequest)) {
                        response.setContentType("text/html;charset=utf-8");
                        System.out.println("ClientServlet login apply");
                        response.setStatus(HttpServletResponse.SC_OK); //200
                    }else {
                        System.out.println("ClientServlet login denied");
                        response.setContentType("text/html;charset=utf-8");
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //401
                    }
                }
                //Метка регистрации в запросе
                if (label.equals("register")) {
                    // TODO: 13.12.2018 Проверка записи в базе и регистрация
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
                    }else{
                        System.out.println("ClientServlet register failed");
                        response.setContentType("text/html;charset=utf-8");
                        response.setStatus(HttpServletResponse.SC_CONFLICT); //409
                    }

                }
            }else{
                System.out.println("Login or password field is empty");
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST); //400
            }

        } catch (JSONException e) {
            throw new IOException("Error parsing JSON request string");
        } catch (Exception e) {

        }
//
//        response.setContentType("text/html;charset=utf-8");
//        System.out.println("ClientServlet working");
//        response.setStatus(HttpServletResponse.SC_OK);
    }

}