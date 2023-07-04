package com.restaurantapp.services;

import com.restaurantapp.models.Ingredient;
import com.restaurantapp.models.Order;
import com.restaurantapp.models.User;
import javafx.collections.ObservableList;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DataService {
    private static ObservableList<User> usersLowerRole;
    private static ObservableList<Ingredient> ingredients;
    private static ObservableList<Order> orders;
    private static final UserService userService = UserService.getInstance();
    private static final StockService stockService = StockService.getInstance();
    private static final OrderService orderService = OrderService.getInstance();
    public static ObservableList<User> getUsersLowerRole(User user) throws SQLException, IOException {
        if (usersLowerRole == null) {
            usersLowerRole = userService.getUsersLowerRole(user);
            log("getUsers");
        }
        return usersLowerRole;
    }
    public static void updateUsers() throws SQLException {
        usersLowerRole = null;
    }
    public static ObservableList<Ingredient> getIngredients() throws SQLException, IOException {
        if (ingredients == null) {
            ingredients = stockService.getIngredients();
            log("getIngredients");
        }
        return ingredients;
    }
    public static void updateIngredients() throws SQLException {
        ingredients = stockService.getIngredients();
    }
    public static ObservableList<Order> getOrders() throws SQLException, IOException {
        if (orders == null) {
            orders = orderService.getOrders();
            log("getOrders");
        }
        return orders;
    }
    private static void log(String log) throws IOException {
        FileWriter fw = new FileWriter("logs.txt", true);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        fw.append(dtf.format(now)).append(": ").append(log).append("\n");
        fw.close();
    }
}
