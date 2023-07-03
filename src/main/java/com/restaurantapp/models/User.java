package com.restaurantapp.models;

import com.restaurantapp.services.UserService;

public class User {
    private String username;
    private String password;
    private int role;
    private static User INSTANCE;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, int role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public static void login(User user) {
        INSTANCE = user;
    }
    public static User getUser() {
        return INSTANCE;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getRole() {
        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
