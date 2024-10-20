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

@WebServlet(name = "Search servlet", urlPatterns = "/search/*")
public class SearchServlet extends HttpServlet {
    private CityRepository cityRepository;

    @Override
    public void init() {
        cityRepository = new CityRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo != null) {
            switch (pathInfo) {
                case "/citizenByLanguage":
                    searchCitizenByLanguage(request, response);
                    break;
                case "/citiesByCitizenType":
                    searchCitiesByCitizenType(request, response);
                    break;
                case "/cityByPopulation":
                    searchCityByPopulation(request, response);
                    break;
                case "/oldestCitizenType":
                    searchOldestCitizenType(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    break;
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void searchCitizenByLanguage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = request.getParameter("language");
        String cityTitle = request.getParameter("cityTitle");

        List<City> cities = cityRepository.findAllByCityTitleAndLanguage(cityTitle, language);

        request.setAttribute("cities", cities);
        request.getRequestDispatcher("/city/cities-main-page.jsp").forward(request, response);
    }

    private void searchCitiesByCitizenType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String citizenType = request.getParameter("citizenType");

        List<City> cities = cityRepository.findAllByCitizenType(citizenType);

        request.setAttribute("cities", cities);
        request.getRequestDispatcher("/city/cities-main-page.jsp").forward(request, response);
    }

    private void searchCityByPopulation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String populationStr = request.getParameter("population");
        int population = Integer.parseInt(populationStr);

        List<City> cities = cityRepository.findAllByPopulationLessThan(population);

        request.setAttribute("cities", cities);
        request.getRequestDispatcher("/city/cities-main-page.jsp").forward(request, response);
    }


    private void searchOldestCitizenType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<City> cities = cityRepository.findAncientTypeWithCity();

        request.setAttribute("cities", cities);
        request.getRequestDispatcher("/city/cities-main-page.jsp").forward(request, response);
    }
}
