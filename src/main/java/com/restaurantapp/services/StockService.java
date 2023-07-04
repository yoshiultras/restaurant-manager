package com.restaurantapp.services;

import com.restaurantapp.DatabaseConnector;
import com.restaurantapp.models.Ingredient;
import com.restaurantapp.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StockService {
    private Connection connection;
    private final static StockService INSTANCE = new StockService();

    public StockService() {
        connection = DatabaseConnector.getConnection();
    }
    public static StockService getInstance() {
        return INSTANCE;
    }
    public ObservableList<Ingredient> getIngredients() throws SQLException {
        ObservableList<Ingredient> ingredients = FXCollections.observableArrayList();
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM ingredients;";
        ResultSet result = statement.executeQuery(query);
        while(result.next()){
            String name = result.getString("name" );
            String unit = result.getString("unit" );
            double amount_in_stock = result.getDouble("amount_in_stock");
            ingredients.add(new Ingredient(name, unit, amount_in_stock));
        }
        return ingredients;
    }
}
