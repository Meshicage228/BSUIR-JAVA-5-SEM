package org.example.repostitory;

import org.example.config.DBConfig;
import org.example.model.CitizenTypes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CityTypesRepository implements CRUDRepository<CitizenTypes, Long>{
    @Override
    public void save(CitizenTypes citizenTypes) {
        try (Connection connection = DBConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(null)) {

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long aLong) {
        try (Connection connection = DBConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(null)) {

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CitizenTypes update(Long aLong, CitizenTypes citizenTypes) {
        try (Connection connection = DBConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(null)) {

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<CitizenTypes> findAll() {
        try (Connection connection = DBConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(null)) {

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return List.of();
    }

    @Override
    public CitizenTypes findById(Long aLong) {
        try (Connection connection = DBConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(null)) {

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
