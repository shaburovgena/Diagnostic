package controller;

import db.DBException;
import db.DBService;
import db.Executor;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;


/**
 * Created by Gena on 16.12.2018.
 * Сервлет добавляющий метрики в базу
 */
public class AgentServlet extends HttpServlet {
    private DBService dbService;
    private JSONObject jsonRequest;

    public AgentServlet(DBService dbService) {
        this.dbService = dbService;
    }

    public AgentServlet(){}
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");

            dispatcher.forward(request, response);


    }



    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        jsonRequest = null;
        StringBuffer sb = new StringBuffer();
        String line = null;
        //Получаем запрос в формате JSON
        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        jsonRequest = new JSONObject(sb.toString());

        String label = jsonRequest.getString("label");

        if (label.equals("metric")) {
            System.out.println("AgentServlet working");
            try {
                dbService.addMetric(jsonRequest.getLong("time"),
                        jsonRequest.getString("title"),
                        jsonRequest.getString("value"));
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (DBException e) {
                e.printStackTrace();
            }
        }


//        response.setContentType("text/html;charset=utf-8");
//        System.out.println("AgentServlet working");
//        response.setStatus(HttpServletResponse.SC_OK);
    }
}