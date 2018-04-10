package ru.alexurtk;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by alex on 10.04.2018.
 */
@WebServlet(urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().append("Olol "+ req.getMethod());
        HttpSession session = req.getSession();

        if (session.getValue("name") == null) {
            session.putValue("name", "TestSession");
            resp.getWriter().append("No session");
        } else {
            resp.getWriter().append((String)session.getValue("name"));
        }


    }
}
