package org.example.servlets;

import org.example.model.City;
import org.example.repostitory.CityRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Cities servlet", urlPatterns = "/cities")
public class CitiesServlet extends HttpServlet {
    private CityRepository cityRepository;

    @Override
    public void init() {
        cityRepository = new CityRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<City> all = cityRepository.findAll();

        req.setAttribute("cities", all);
        req.getRequestDispatcher("/city/cities-main-page.jsp").forward(req, resp);
    }
}
