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
import java.util.Arrays;

public class UserService {
    private Connection connection;

    public UserService() {
        connection = DatabaseConnector.getConnection();
    }

    public boolean addUser(String username, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if(!username.matches("[A-Za-z0-9]+") || !password.matches("[A-Za-z0-9]+")) return false;
        User user = new User(username, passwordHashing(password));
        return true;
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
        System.out.println(us.addUser("nik", "1234"));

    }
}
