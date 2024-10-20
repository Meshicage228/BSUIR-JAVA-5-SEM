package org.example.config;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.experimental.UtilityClass;
import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

@UtilityClass
public class DBConfig {

    static {
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        try {
            Properties info = new Properties();
            info.setProperty("url", "jdbc:postgresql://localhost:15431/labDB");
            info.setProperty("user", "testMan");
            info.setProperty("password", "123123");
            info.setProperty("useUnicode", "true");
            info.setProperty("characterEncoding", "utf8");
            return DriverManager.getConnection ("jdbc:postgresql://localhost:15431/labDB", info);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void liquibaseStart(){
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()){

            String schemaName = "sequences";
            String createSchemaQuery = "CREATE SCHEMA IF NOT EXISTS " + schemaName;

            statement.executeUpdate(createSchemaQuery);
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));

            Liquibase liquibase = new Liquibase("db.changelog/change-logs-master.xml", new ClassLoaderResourceAccessor(), database);
            liquibase.update();
            System.out.println();
        } catch (LiquibaseException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
