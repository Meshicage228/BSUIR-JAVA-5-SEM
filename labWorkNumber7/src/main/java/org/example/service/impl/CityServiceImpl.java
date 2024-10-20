package org.example.service.impl;

import org.example.model.City;
import org.example.repostitory.CityRepository;
import org.example.service.CityService;

import java.util.List;

public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City getById(Long id) {
        return cityRepository.findById(id);
    }

    @Override
    public void save(City city) {
        cityRepository.save(city);
    }

//    @Override
//    public void delete(Long cityId) {
//        cityRepository.deleteById(cityId);
//    }

    @Override
    public City update(Long id, City city) {
        return cityRepository.update(id, city);
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }
}
