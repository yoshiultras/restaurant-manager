package com.restaurantapp.services;

import com.restaurantapp.DatabaseConnector;
import com.restaurantapp.models.Client;
import com.restaurantapp.models.Table;
import com.restaurantapp.models.Waiter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RestaurantService {
    private final Connection connection;
    private final static RestaurantService INSTANCE = new RestaurantService();
    public RestaurantService() {
        connection = DatabaseConnector.getConnection();
    }
    public static RestaurantService getInstance() {
        return INSTANCE;
    }
    public ObservableList<Table> getTable() throws SQLException {
        ObservableList<Table> tables = FXCollections.observableArrayList();
        Statement statement = connection.createStatement();
        String query = "SELECT id FROM tables;";
        ResultSet result = statement.executeQuery(query);
        while(result.next()){
            tables.add(new Table(result.getInt("id")));
        }
        return tables;
    }

    public ObservableList<Waiter> getWaiters() throws SQLException {
        ObservableList<Waiter> waiters = FXCollections.observableArrayList();
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM waiters;";
        ResultSet result = statement.executeQuery(query);
        while(result.next()){
            waiters.add(new Waiter(result.getInt("id"), result.getString("last_name"), result.getString("first_name"), result.getString("second_name")));
        }
        return waiters;
    }

    public ObservableList<Client> getClients() throws SQLException {
        ObservableList<Client> clients = FXCollections.observableArrayList();
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM clients;";
        ResultSet result = statement.executeQuery(query);
        while(result.next()){
            clients.add(new Client(result.getInt("id"), result.getString("last_name"), result.getString("first_name"), result.getString("second_name")));
        }
        return clients;
    }
}
