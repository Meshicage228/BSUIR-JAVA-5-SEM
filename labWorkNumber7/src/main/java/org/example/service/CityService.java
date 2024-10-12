package org.example.service;

import org.example.model.City;

import java.util.List;

public interface CityService {
    City getById(Long id);
    void save(City city);
    void delete(Long city);
    City update(Long id ,City city);
    List<City> findAll();
}
