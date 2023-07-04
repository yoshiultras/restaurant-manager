package com.restaurantapp.services;

import com.restaurantapp.DatabaseConnector;
import com.restaurantapp.models.Dish;
import com.restaurantapp.models.Dishes;
import com.restaurantapp.models.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderService {
    private final Connection connection;
    private final static OrderService INSTANCE = new OrderService();

    public OrderService() {
        connection = DatabaseConnector.getConnection();
    }
    public static OrderService getInstance() {
        return INSTANCE;
    }
    public ObservableList<Order> getOrders() throws SQLException {
        ObservableList<Order> orders = FXCollections.observableArrayList();
        Statement statement = connection.createStatement();
        String query = "SELECT meals.id, meal_date, start_time, end_time, clients.last_name, clients.first_name, clients.second_name, " +
                "waiters.last_name, waiters.first_name, waiters.second_name, table_id FROM meals JOIN clients ON clients.id = client_id JOIN waiters ON waiters.id = waiter_id ORDER BY 2, 1;";
        ResultSet result = statement.executeQuery(query);
        while(result.next()){
            Order order = order(result);
            String clientName = result.getString("clients.last_name") + " " + result.getString("clients.first_name") + " " + result.getString("clients.second_name");
            order.setClient(clientName);
            orders.add(order);
        }
        query = "SELECT meals.id, meal_date, start_time, end_time, " +
                "waiters.last_name, waiters.first_name, waiters.second_name, table_id FROM meals JOIN waiters ON waiters.id = waiter_id WHERE client_id IS NULL ORDER BY 2, 1;";
        result = statement.executeQuery(query);
        while(result.next()){
            Order order = order(result);
            orders.add(order);
        }
        return orders;
    }
    private Order order(ResultSet result) throws SQLException {
        int mealId = result.getInt("id");
        String mealDate = result.getString("meal_date");
        String startTime = result.getString("start_time");
        String endTime = result.getString("end_time");
        String waiterName = result.getString("waiters.last_name") + " " + result.getString("waiters.first_name") + " " + result.getString("waiters.second_name");
        int table = result.getInt("table_id");
        String q1 = "SELECT dish_name FROM orders WHERE meal_id = " + mealId + ";";
        Statement statement1 = connection.createStatement();
        ResultSet newResult = statement1.executeQuery(q1);
        Dishes dishes = new Dishes();
        while(newResult.next()) {
            dishes.add(new Dish(newResult.getString("dish_name")));
        }
        return new Order(dishes, mealDate, startTime, endTime, waiterName, table);
    }
}