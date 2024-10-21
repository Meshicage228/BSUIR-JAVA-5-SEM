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

@WebServlet(name = "Create new city servlet", urlPatterns = "/cities/create")
public class CreateCityServlet extends HttpServlet {
    private CityRepository cityRepository;
    private ObjectMapper objectMapper;

    @Override
    public void init() {
        cityRepository = new CityRepository();
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.sendRedirect("/city/city-create.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        String jsonString = objectMapper.writeValueAsString(new HashMap<String, Object>() {{
            put("title", req.getParameter("title"));
            put("square", Integer.parseInt(req.getParameter("square")));
            put("foundationYear", req.getParameter("foundationYear"));
        }});

        City city = objectMapper.readValue(jsonString, City.class);

        cityRepository.save(city);

        req.setAttribute("Success-save", "city successfully saved!");

        req.getRequestDispatcher("/city/city-create.jsp").forward(req, resp);
    }
}
