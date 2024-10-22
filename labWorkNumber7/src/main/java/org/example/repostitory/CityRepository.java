package org.example.repostitory;

import org.example.config.DBConfig;
import org.example.enums.CitizenType;
import org.example.model.CitizenTypes;
import org.example.model.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.util.SQLscripts.*;

public class CityRepository implements CRUDRepository<City, Long> {
    @Override
    public void save(City city) {
        executeUpdate(CREATE_CITY, ps -> {
            ps.setString(1, city.getTitle());
            ps.setFloat(2, city.getSquare());
            ps.setDate(3, Date.valueOf(city.getFoundationYear()));
        });
    }

    @Override
    public City update(Long id, City city) {
        executeUpdate(UPDATE_CITY, ps -> {
            ps.setString(1, city.getTitle());
            ps.setFloat(2, city.getSquare());
            ps.setDate(3, Date.valueOf(city.getFoundationYear()));
            ps.setLong(4, id);
        });
        return findById(id);
    }

    @Override
    public List<City> findAll() {
        return executeQuery(GET_ALL_CITIES);
    }

    @Override
    public City findById(Long id) {
        List<City> cities = executeQuery(GET_CITY_BY_ID, ps -> ps.setLong(1, id));
        return cities.isEmpty() ? null : cities.get(0);
    }

    public List<City> findAllByCityTitleAndLanguage(String cityTitle, String language) {
        return executeQuery(SELECT_CITIZENS_BY_CITY_TITLE_AND_LANGUAGE, ps -> {
            ps.setString(1, cityTitle);
            ps.setString(2, language);
        });
    }

    public List<City> findAllByCitizenType(String citizenType) {
        return executeQuery(SELECT_CITIES_BY_CITY_TITLE, ps -> ps.setString(1, citizenType));
    }

    public List<City> findAllByPopulationLessThan(Integer population) {
        return executeQuery(SELECT_POPULATION_LESS_THAN, ps -> ps.setInt(1, population));
    }

    public List<City> findAncientTypeWithCity() {
        return executeQuery(SELECT_ANCIENT_TYPE);
    }

    private List<City> executeQuery(String sql, PreparedStatementSetter setter) {
        List<City> cities = new ArrayList<>();
        try (Connection connection = DBConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            if (setter != null) {
                setter.setValues(preparedStatement);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cities.add(mapCity(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cities;
    }

    private List<City> executeQuery(String sql) {
        return executeQuery(sql, null);
    }

    @Override
    public void deleteById(Long id){
        try (Connection connection = DBConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CITY_BY_ID)) {
            deleteCategoryByCityId(id);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteCategoryByCityId(Long id){
        try (Connection connection = DBConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CATEGORY_BY_CITY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void executeUpdate(String sql, PreparedStatementSetter setter) {
        try (Connection connection = DBConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            setter.setValues(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private City mapCity(ResultSet resultSet) throws SQLException {
        return City.builder()
                .id(resultSet.getLong("id"))
                .title(resultSet.getString("title"))
                .square(resultSet.getFloat("square"))
                .foundationYear(resultSet.getString("year_foundation"))
                .citizenTypesList(findByCityId(resultSet.getLong("id")))
                .build();
    }

    private CitizenTypes mapCitizenType(ResultSet resultSet) throws SQLException {
        return CitizenTypes.builder()
                .id(resultSet.getLong("id"))
                .type(CitizenType.valueOf(resultSet.getString("type")))
                .spokenLanguage(resultSet.getString("language"))
                .population(resultSet.getLong("population"))
                .build();
    }

    private List<CitizenTypes> findByCityId(Long cityId) {
        List<CitizenTypes> citizenTypes = new ArrayList<>();
        try (Connection connection = DBConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_CITIZENS_BY_CITY_ID)) {
            preparedStatement.setLong(1, cityId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                citizenTypes.add(mapCitizenType(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return citizenTypes;
    }

    @FunctionalInterface
    interface PreparedStatementSetter {
        void setValues(PreparedStatement ps) throws SQLException;
    }
}
