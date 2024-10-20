package org.example.service;

import org.example.model.CitizenTypes;

public interface CitizenTypesService {
    CitizenTypes getById(Long id);
    void save(CitizenTypes citizenTypes);
    CitizenTypes update(Long id, CitizenTypes citizenTypes);
//    void delete(Long id);
}
