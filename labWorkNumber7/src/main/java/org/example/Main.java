package org.example;

import org.example.config.DBConfig;

public class Main {
    public static void main(String[] args) {
        DBConfig.liquibaseStart();
        System.out.println();
    }
}