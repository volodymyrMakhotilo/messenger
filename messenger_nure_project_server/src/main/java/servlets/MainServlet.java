package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.User;
import utilities.db;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

import main.Repository;
import main.*;


@WebServlet("/test")
public class MainServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        Api api = new Api(new Service(new Repository(db.getSessionFactory())));
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            var writer = resp.getWriter();
            var reader = req.getReader();
            writer.println(req.getParameter("id"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            var writer = resp.getWriter();
            String requestData = req.getReader().lines().collect(Collectors.joining());
            writer.println(requestData);
            writer.close();

            /*ObjectMapper mapper = new ObjectMapper();
            User user = mapper.readValue(req.getInputStream(),User.class);
            writer.println(user);
*/
        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }
}
