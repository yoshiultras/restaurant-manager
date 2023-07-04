package com.restaurantapp.services;

import com.restaurantapp.models.Ingredient;
import com.restaurantapp.models.Order;
import com.restaurantapp.models.User;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public final class DataService {
    private static ObservableList<User> usersLowerRole;
    private static ObservableList<Ingredient> ingredients;
    private static ObservableList<Order> orders;
    private static UserService userService = UserService.getInstance();
    private static StockService stockService = StockService.getInstance();
    private static OrderService orderService = OrderService.getInstance();
    private final static DataService INSTANCE = new DataService();
    public static DataService getInstance() {
        return INSTANCE;
    }
    public static ObservableList<User> getUsersLowerRole(User user) throws SQLException {
        if (usersLowerRole == null) {
            usersLowerRole = userService.getUsersLowerRole(user);
        }
        return usersLowerRole;
    }
    public static ObservableList<Ingredient> getIngredients() throws SQLException {
        if (ingredients == null) {
            ingredients = stockService.getIngredients();
        }
        return ingredients;
    }
    public static void updateIngredients() throws SQLException {
        ingredients = stockService.getIngredients();
    }
    public static ObservableList<Order> getOrders() throws SQLException {
        if (orders == null) {
            orders = orderService.getOrders();
        }
        return orders;
    }
}
