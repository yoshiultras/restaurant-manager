package com.restaurantapp.services;

import com.restaurantapp.models.User;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public final class DataService {
    private static ObservableList<User> usersLowerRole;
    private static UserService userService = UserService.getInstance();
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
}
