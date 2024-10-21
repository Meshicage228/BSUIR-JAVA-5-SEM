package org.example.servlets;

import org.example.model.City;
import org.example.repostitory.CityRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

@WebServlet(name = "Search servlet", urlPatterns = "/search/*")
public class SearchServlet extends HttpServlet {
    private CityRepository cityRepository;

    private final HashMap<Predicate<String>, BiConsumer<HttpServletRequest, HttpServletResponse>> handler = new HashMap<>(){{
        put(path -> path.equals("/citizenByLanguage"), (req, res) -> searchCitizenByLanguage(req, res));
        put(path -> path.equals("/citiesByCitizenType"), (req, res) -> searchCitiesByCitizenType(req, res));
        put(path -> path.equals("/cityByPopulation"), (req, res) -> searchCityByPopulation(req, res));
        put(path -> path.equals("/oldestCitizenType"), (req, res) -> searchOldestCitizenType(req, res));
    }};

    @Override
    public void init() {
        cityRepository = new CityRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String pathInfo = request.getPathInfo();

        handler.entrySet().stream()
                .filter(entrySet -> pathInfo != null)
                .filter(entrySet -> entrySet.getKey().test(pathInfo))
                .findAny()
                .ifPresentOrElse(
                        entrySet -> entrySet.getValue().accept(request, response),
                        () -> {
                            throw new RuntimeException("Endpoint doesn't found");
                        }
                );
    }

    private void searchCitizenByLanguage(HttpServletRequest request, HttpServletResponse response) {
        String language = request.getParameter("language");
        String cityTitle = request.getParameter("cityTitle");

        List<City> cities = cityRepository.findAllByCityTitleAndLanguage(cityTitle, language);

        request.setAttribute("cities", cities);
        try {
            request.getRequestDispatcher("/city/cities-main-page.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void searchCitiesByCitizenType(HttpServletRequest request, HttpServletResponse response) {
        String citizenType = request.getParameter("citizenType");

        List<City> cities = cityRepository.findAllByCitizenType(citizenType);

        request.setAttribute("cities", cities);
        try {
            request.getRequestDispatcher("/city/cities-main-page.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void searchCityByPopulation(HttpServletRequest request, HttpServletResponse response) {
        String populationStr = request.getParameter("population");
        int population = Integer.parseInt(populationStr);

        List<City> cities = cityRepository.findAllByPopulationLessThan(population);

        request.setAttribute("cities", cities);
        try {
            request.getRequestDispatcher("/city/cities-main-page.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void searchOldestCitizenType(HttpServletRequest request, HttpServletResponse response) {
        List<City> cities = cityRepository.findAncientTypeWithCity();

        request.setAttribute("cities", cities);
        try {
            request.getRequestDispatcher("/city/cities-main-page.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
