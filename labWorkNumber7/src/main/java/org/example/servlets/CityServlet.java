package org.example.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.City;
import org.example.service.CityService;
import org.example.util.AppFactory;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "Main page", urlPatterns = "/citySinglePage")
public class CityServlet extends HttpServlet {
    private CityService cityService;
    private ObjectMapper objectMapper;

    @Override
    public void init() {
        cityService = AppFactory.getCityService();
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.sendRedirect("/city/city-create.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String jsonString = objectMapper.writeValueAsString(new HashMap<String, Object>() {{
            put("title", req.getParameter("title"));
            put("square", Integer.parseInt(req.getParameter("square")));
            put("foundationYear", req.getParameter("foundationYear"));
        }});

        City city = objectMapper.readValue(jsonString, City.class);

        cityService.save(city);

        resp.sendRedirect("/city/city-create.jsp");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("update");
        resp.sendRedirect("/city/city-create.jsp");
    }
}
