package servlet;

import db.DBService;
import db.Executor;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Gena on 17.07.2018.
 * Сервлет отображающий изменения
 */
public class ClientServlet extends HttpServlet {
    private DBService dbService;
    private Executor executor;

    public ClientServlet() {
//        dbService = new DBService();
    }


    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        // TODO: 11.12.2018 обработка данных с клиента

        response.setContentType("text/html;charset=utf-8");
        System.out.println("ClientServlet working");
        response.setStatus(HttpServletResponse.SC_OK);
    }

}