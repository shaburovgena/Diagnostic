package main;

import db.DBService;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlet.AgentServlet;
import servlet.ClientServlet;

class StartServer {
    Server server;
    DBService dbService;

    StartServer() {
//        dbService = new DBService();
//        try {
//            long userId = dbService.addUser("test", "testPsw", "Ivanov Ivan Ivanovich", "89101234567", "ivanov@example.com");
//            userId = dbService.addUser("admin", "adminPsw", "Sidorov Sidr", "123456", "sidorov@example.com");
//            userId = dbService.addUser("user", "userPsw", "Petrov petr", "654321", "petrov@example.com");
//            dbService.printConnectInfo();
//        } catch (
//                DBException e) {
//            e.printStackTrace();
//        }

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        //Сервер для приема данных с агента
        context.addServlet(new ServletHolder(new AgentServlet()), "/agent");
        //Сервер обработки данных  с клиента
        context.addServlet(new ServletHolder(new ClientServlet()), "/client");
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
