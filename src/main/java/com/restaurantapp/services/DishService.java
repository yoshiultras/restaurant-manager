package com.restaurantapp.services;

import com.restaurantapp.data.DatabaseConnector;
import com.restaurantapp.models.Dish;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class DishService {
    private final Connection connection;
    private final static DishService INSTANCE = new DishService();
    public DishService() {
        connection = DatabaseConnector.getConnection();
    }
    public static DishService getInstance() {
        return INSTANCE;
    }

    public ObservableList<Dish> getPopularDishes() throws SQLException {
        ObservableList<Dish> dishes = FXCollections.observableArrayList();
        Statement statement = connection.createStatement();
        String query = "SELECT dishes.name FROM dishes JOIN orders ON dishes.name = orders.dish_name GROUP BY 1 ORDER BY COUNT(*) DESC, 1 DESC LIMIT 3;";
        ResultSet result = statement.executeQuery(query);
        while(result.next()){
            dishes.add(new Dish(result.getString("dishes.name")));
        }
        return dishes;
    }
    public ObservableList<Dish> getUnpopularDishes() throws SQLException {
        ObservableList<Dish> dishes = FXCollections.observableArrayList();
        Statement statement = connection.createStatement();
        String query = "SELECT dishes.name FROM dishes JOIN orders ON dishes.name = orders.dish_name GROUP BY 1 ORDER BY COUNT(*) ASC, 1 ASC LIMIT 3;";
        ResultSet result = statement.executeQuery(query);
        while(result.next()){
            dishes.add(new Dish(result.getString("dishes.name")));
        }
        return dishes;
    }

    public ObservableList<Dish> getDishes() throws SQLException {
        ObservableList<Dish> dishes = FXCollections.observableArrayList();
        Statement statement = connection.createStatement();
        String query = "SELECT name FROM dishes;";
        ResultSet result = statement.executeQuery(query);
        while(result.next()){
            dishes.add(new Dish(result.getString("name")));
        }
        return dishes;
    }
}
