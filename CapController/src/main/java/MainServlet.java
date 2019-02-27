import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {

    public MainServlet() {
    }


    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("value", 555);
        response.setStatus(200);
        response.setContentType("application/json");
        response.getWriter().print(jsonObject);
    }
}
