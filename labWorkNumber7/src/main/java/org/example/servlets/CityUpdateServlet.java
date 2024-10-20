package org.example.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.City;
import org.example.repostitory.CityRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "Update city servlet", urlPatterns = "/city/update/*")
public class CityUpdateServlet extends HttpServlet {
    private CityRepository cityRepository;
    private ObjectMapper objectMapper;

    @Override
    public void init() {
        cityRepository = new CityRepository();
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathVariable = req.getPathInfo();
        pathVariable = pathVariable.substring(1);

        City city = cityRepository.findById(Long.parseLong(pathVariable));

        req.setAttribute("city", city);

        req.getRequestDispatcher("/city/city-single-update-page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String pathVariable = req.getPathInfo();
        pathVariable = pathVariable.substring(1);

        String jsonString = objectMapper.writeValueAsString(new HashMap<String, Object>() {{
            put("title", req.getParameter("title"));
            put("square", Float.parseFloat(req.getParameter("square")));
            put("foundationYear", req.getParameter("foundationYear"));
        }});

        City city = objectMapper.readValue(jsonString, City.class);

        City updated = cityRepository.update(Long.parseLong(pathVariable), city);

        req.setAttribute("city", updated);
        req.getRequestDispatcher("/city/city-single-update-page.jsp").forward(req, resp);
    }
}
