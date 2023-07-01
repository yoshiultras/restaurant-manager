package com.restaurantapp.models;

public class User {
    private String username;
    private String password;
    private int role;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
