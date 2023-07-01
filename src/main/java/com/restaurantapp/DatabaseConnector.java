package com.restaurantapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String DB_URL = "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2313_restaurant";
    private static final String DB_USER = "std_2313_restaurant";
    private static final String DB_PASSWORD = "12345678";

    private static Connection connection;

    public DatabaseConnector() {
        try {
            // Загрузка драйвера MySQL JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Установка соединения с базой данных
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Проверка соединения
            if (connection != null) {
                System.out.println("Соединение с базой данных успешно установлено!");
                // Дополнительные проверки и операции с базой данных
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Драйвер JDBC не найден");
        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных: " + e.getMessage());
        }

    }

    public static Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Соединение с базой данных закрыто.");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при закрытии соединения с базой данных: " + e.getMessage());
        }
    }
}


