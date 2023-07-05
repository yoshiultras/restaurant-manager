package com.restaurantapp.services;

import com.restaurantapp.models.*;
import javafx.collections.ObservableList;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DataService {
    private static ObservableList<User> usersLowerRole;
    private static ObservableList<Ingredient> ingredients;
    private static ObservableList<Table> tables;
    private static ObservableList<Waiter> waiters;
    private static ObservableList<Client> clients;
    private static ObservableList<Dish> dishes;
    private static ObservableList<Dish> popularDishes;
    private static ObservableList<Dish> unpopularDishes;
    private static ObservableList<Order> orders;
    private static final UserService userService = UserService.getInstance();
    private static final StockService stockService = StockService.getInstance();
    private static final OrderService orderService = OrderService.getInstance();
    private static final DishService dishService = DishService.getInstance();
    private static final RestaurantService restaurantService = RestaurantService.getInstance();
    public static ObservableList<User> getUsers(User user) throws SQLException, IOException {
        if (usersLowerRole == null) {
            usersLowerRole = userService.getUsers(user);
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
    public static ObservableList<Table> getTables() throws IOException, SQLException {
        if (tables == null) {
            tables = restaurantService.getTable();
            log("getDishes");
        }
        return tables;
    }
    public static ObservableList<Waiter> getWaiters() throws IOException, SQLException {
        if (waiters == null) {
            waiters = restaurantService.getWaiters();
            log("getDishes");
        }
        return waiters;
    }
    public static ObservableList<Client> getClients() throws IOException, SQLException {
        if (clients == null) {
            clients = restaurantService.getClients();
            log("getDishes");
        }
        return clients;
    }
    public static ObservableList<Order> getOrders() throws SQLException, IOException {
        if (orders == null) {
            orders = orderService.getOrders();
            log("getOrders");
        }
        return orders;
    }
    public static ObservableList<Dish> getDishes() throws IOException, SQLException {
        if (dishes == null) {
            dishes = dishService.getDishes();
            log("getDishes");
        }
        return dishes;
    }
    public static ObservableList<Dish> getPopularDishes() throws IOException, SQLException {
        if (popularDishes == null) {
            popularDishes = dishService.getPopularDishes();
            log("getPopularDishes");
        }
        return popularDishes;
    }
    public static ObservableList<Dish> getUnpopularDishes() throws IOException, SQLException {
        if (unpopularDishes == null) {
            unpopularDishes = dishService.getUnpopularDishes();
            log("getUnpopula rDishes");
        }
        return unpopularDishes;
    }
    private static void log(String log) throws IOException {
        FileWriter fw = new FileWriter("logs.txt", true);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        fw.append(dtf.format(now)).append(": ").append(log).append("\n");
        fw.close();
    }
}
