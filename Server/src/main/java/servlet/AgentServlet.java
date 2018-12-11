package servlet;

import db.DBException;
import db.DBService;
import db.Executor;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;


/**
 * Created by Gena on 16.07.2018.
 * Сервлет добавляющий запись в базу
 */
public class AgentServlet extends HttpServlet {
    private DBService dbService;
    private Executor executor;

    public AgentServlet() {
//        dbService = new DBService();
    }


    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        // TODO: 11.12.2018 обработка приема данных с агента

        response.setContentType("text/html;charset=utf-8");
        System.out.println("AgentServlet working");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}