package org.example.service.impl;

import org.example.model.CitizenTypes;
import org.example.repostitory.CityTypesRepository;
import org.example.service.CitizenTypesService;

public class CitizenTypesServiceImpl implements CitizenTypesService {
    private final CityTypesRepository cityTypesRepository;

    public CitizenTypesServiceImpl(CityTypesRepository cityRepository) {
        this.cityTypesRepository = cityRepository;
    }

    @Override
    public CitizenTypes getById(Long id) {
        return cityTypesRepository.findById(id);
    }

    @Override
    public void save(CitizenTypes citizenTypes) {
        cityTypesRepository.save(citizenTypes);
    }

    @Override
    public CitizenTypes update(Long id, CitizenTypes citizenTypes) {
        return cityTypesRepository.update(id, citizenTypes);
    }

    @Override
    public void delete(Long id) {
        cityTypesRepository.deleteById(id);
    }
}
