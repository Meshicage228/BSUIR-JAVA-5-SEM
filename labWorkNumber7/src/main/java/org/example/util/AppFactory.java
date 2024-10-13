package org.example.util;

import org.example.repostitory.CityRepository;
import org.example.repostitory.CityTypesRepository;
import org.example.service.impl.CitizenTypesServiceImpl;
import org.example.service.impl.CityServiceImpl;

public class AppFactory {
    private AppFactory() {
    }

    public static CityRepository getCityRepository() {
        return new CityRepository();
    }

    public static CityTypesRepository getCityTypesRepository() {
        return new CityTypesRepository();
    }

    public static CitizenTypesServiceImpl getCitizenTypesService() {
        return new CitizenTypesServiceImpl(getCityTypesRepository());
    }

    public static CityServiceImpl getCityService() {
        return new CityServiceImpl(getCityRepository());
    }
}
