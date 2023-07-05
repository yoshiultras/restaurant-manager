package com.restaurantapp.models;

public class User {
    private String username;
    private String password;
    private String role;
    private String lastName;
    private String firstName;
    private String secondName;
    private String fullName;
    private static User INSTANCE;

    public User(String username, String password, String role, String lastName, String firstName, String secondName) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.lastName = lastName;
        this.firstName = firstName;
        this.secondName = secondName;
        fullName = lastName + " " + firstName + " " + secondName;
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

    public String getRole() {
        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
