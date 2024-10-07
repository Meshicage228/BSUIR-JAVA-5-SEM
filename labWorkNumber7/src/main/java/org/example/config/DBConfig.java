package org.example.config;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.postgresql.Driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBConfig {
    private static final String CONFIG_FILE = "labWorkNumber7/src/main/resources/configs/dataBase.properties";

    private static String URL;

    private static String USER;

    private static String PASSWORD;

    private static String useUnicode;

    private static String characterEncoding;

    static {
        try {
            loadConfig();
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadConfig() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            properties.load(fis);
            URL = properties.getProperty("database.url");
            USER = properties.getProperty("database.user");
            PASSWORD = properties.getProperty("database.password");
            useUnicode = properties.getProperty("database.useUnicode");
            characterEncoding = properties.getProperty("database.characterEncoding");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        try {
            Properties info = new Properties();
            info.setProperty("user", USER);
            info.setProperty("password",PASSWORD);
            info.setProperty("useUnicode",useUnicode);
            info.setProperty("characterEncoding",characterEncoding);
            return DriverManager.getConnection (URL, info);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void liquibaseStart(){
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
