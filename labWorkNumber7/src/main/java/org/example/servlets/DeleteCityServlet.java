package org.example.servlets;

import org.example.repostitory.CityRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Delete city servlet", urlPatterns = "/city/delete/*")
public class DeleteCityServlet extends HttpServlet {
    private CityRepository cityRepository;

    @Override
    public void init() {
        cityRepository = new CityRepository();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        Long pathVar = Long.parseLong(pathInfo.substring(1));

        cityRepository.deleteById(pathVar);

        resp.sendRedirect("/cities");
    }
}
