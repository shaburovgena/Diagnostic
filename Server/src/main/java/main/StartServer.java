package main;

import db.DBService;
import db.Executor;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import service.Checker;
import service.RandomUsers;
import controller.AgentServlet;
import controller.ClientServlet;

import java.sql.SQLException;

class StartServer {
//    private final Checker checker;
    Server server;
//    DBService dbService;
//    RandomUsers randomUsers;

    StartServer() {
//        Executor executor = null;
//        dbService = new DBService();
//        //Вызывается однократно для заполнения базы
//        randomUsers = new RandomUsers(dbService);
//        randomUsers.getRandomUserData();
//        try {
//            executor = new Executor(dbService.connection());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        checker = new Checker(executor);
//
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
//        //Сервер для приема данных с агента
//        context.addServlet(new ServletHolder(new AgentServlet(dbService)), "/agent");
//        //Сервер обработки данных  с клиента
//        context.addServlet(new ServletHolder(new ClientServlet(checker, dbService)), "/client");
        context.addServlet(new ServletHolder(new AgentServlet()), "/agent");
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resourceHandler, context});

        server = new Server(8443);
        server.setHandler(handlers);
        try {
            server.start();
            System.out.println("Server started on port " + 8443);
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
