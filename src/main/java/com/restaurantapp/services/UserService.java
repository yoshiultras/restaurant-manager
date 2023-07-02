package com.restaurantapp.services;

import com.restaurantapp.DatabaseConnector;
import com.restaurantapp.models.User;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserService {
    private Connection connection;

    public UserService() {
        connection = DatabaseConnector.getConnection();
    }

    public User addUser(String username, String password) throws NoSuchAlgorithmException, InvalidKeySpecException, SQLException {
        if(exists(username)) return null;
        Statement statement = connection.createStatement();
        password = passwordHashing(password);
        String query = "INSERT INTO login(username, password, role, id) VALUES ('" + username + "', '" + password + "', 0, NULL)";
        statement.executeUpdate(query);
        User user = new User(username, password, 0);
        return user;
    }
    public void changeName(User user, String newName) throws SQLException {
        String username = user.getUsername();
        String password = user.getPassword();
        Statement statement = connection.createStatement();
        String query = "UPDATE login SET username = '" + newName + "' WHERE username = '" + username + "' AND password = '" + password + "';";
        statement.executeUpdate(query);
    }
    public void changePassword(User user, String newPassword) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        String username = user.getUsername();
        String password = user.getPassword();
        newPassword = passwordHashing(newPassword);
        Statement statement = connection.createStatement();
        String query = "UPDATE login SET password = '" + newPassword + "' WHERE username = '" + username + "' AND password = '" + password + "';";
        statement.executeUpdate(query);
    }
    public boolean exists(String username) throws SQLException {
        Statement statement = connection.createStatement();
        String query = "SELECT username, password, role FROM login WHERE username = '" + username + "';";
        ResultSet result = statement.executeQuery(query);
        return result.next();
    }

    public User userLogin(String username, String password) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        if(!username.matches("[A-Za-z0-9]+") || !password.matches("[A-Za-z0-9]+")) return null;
        Statement statement = connection.createStatement();
        String query = "SELECT username, password, role FROM login WHERE username = '" + username + "' AND password = '" + passwordHashing(password) + "';";
        ResultSet result = statement.executeQuery(query);
        if(!result.next()) return null;
        return new User(result.getString("username"), result.getString("password"), result.getInt("role"));
    }

    public String passwordHashing(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : hash) {
            stringBuilder.append(b);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        UserService us = new UserService();
        System.out.println(us.passwordHashing("1234"));
    }
}
