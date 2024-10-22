package org.example.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SQLscripts {
    public String CREATE_CITY = "INSERT INTO cities (title, square, year_foundation) VALUES (?, ?, ?)";
    public String GET_ALL_CITIES = "SELECT * FROM cities";
    public String GET_CITY_BY_ID = "SELECT * FROM cities WHERE id = ?";
    public String UPDATE_CITY = """
            UPDATE cities
            SET title = ?, square = ?, year_foundation = ?
            WHERE id = ?;
            """;
    public String GET_CITIZENS_BY_CITY_ID = """
            SELECT * FROM "citizenTypes"
            JOIN public.cities c ON c.id = "citizenTypes".city_id
            WHERE city_id = ?
            """;
    public String SELECT_CITIZENS_BY_CITY_TITLE_AND_LANGUAGE = """
            SELECT * FROM "citizenTypes" as type
            JOIN public.cities c on c.id = type.city_id
            WHERE c.title LIKE ? AND type.language = ?
            """;
    public String SELECT_CITIES_BY_CITY_TITLE = """
            SELECT * FROM "citizenTypes" AS tp
            JOIN public.cities c ON c.id = tp.city_id
            WHERE tp.type = ?
            """;
    public String SELECT_POPULATION_LESS_THAN = """
            SELECT *
            FROM cities
                     JOIN (SELECT SUM(population) AS popSum, city_id
                           FROM "citizenTypes"
                           GROUP BY city_id
                           ORDER BY city_id) AS res ON res.city_id = cities.id
                     JOIN public."citizenTypes" cT ON cities.id = cT.city_id
            WHERE res.popSum <= ?
            """;
    public String SELECT_ANCIENT_TYPE = """
            SELECT * FROM "citizenTypes"
            JOIN public.cities c on c.id = "citizenTypes".city_id
            ORDER BY c.year_foundation ASC
            LIMIT 1;
            """;

    public String DELETE_CITY_BY_ID = """
            DELETE FROM cities
            WHERE id = ?;
            """;
    public String DELETE_CATEGORY_BY_CITY_ID = """
            DELETE FROM "citizenTypes"
            WHERE city_id = ?;
            """;
}
