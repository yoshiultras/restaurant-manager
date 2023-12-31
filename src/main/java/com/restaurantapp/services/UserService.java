package com.restaurantapp.services;

import com.restaurantapp.data.DatabaseConnector;
import com.restaurantapp.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

public final class UserService {
    private final Connection connection;
    private final static UserService INSTANCE = new UserService();

    public UserService() {
        connection = DatabaseConnector.getConnection();
    }
    public static UserService getInstance() {
        return INSTANCE;
    }

        public User addUser(String username, String password, String lastName, String firstName, String secondName) throws NoSuchAlgorithmException, InvalidKeySpecException, SQLException {
        if(exists(username)) return null;
        Statement statement = connection.createStatement();
        password = passwordHashing(password);
        String query = "INSERT INTO users(username, password, role, id, last_name, first_name, second_name) VALUES ('"
                + username + "', '" + password + "', 'Без роли', NULL, '" + lastName + "', '" + firstName + "', + '" + secondName + "')";
        statement.executeUpdate(query);
        User user = new User(username, password, "0", lastName, firstName, secondName);
        return user;
    }
    public void changeName(User user, String newName) throws SQLException {
        String username = user.getUsername();
        String password = user.getPassword();
        Statement statement = connection.createStatement();
        String query = "UPDATE users SET username = '" + newName + "' WHERE username = '" + username + "' AND password = '" + password + "';";
        statement.executeUpdate(query);
    }
    public void changePassword(User user, String newPassword) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        String username = user.getUsername();
        String password = user.getPassword();
        newPassword = passwordHashing(newPassword);
        Statement statement = connection.createStatement();
        String query = "UPDATE users SET password = '" + newPassword + "' WHERE username = '" + username + "' AND password = '" + password + "';";
        statement.executeUpdate(query);
    }
    public void deleteUser(User user) throws SQLException {
        Statement statement = connection.createStatement();
        String query = "DELETE FROM users WHERE username = '" + user.getUsername() + "'";
        statement.executeUpdate(query);
    }
    public boolean exists(String username) throws SQLException {
        Statement statement = connection.createStatement();
        String query = "SELECT username, password, role FROM users WHERE username = '" + username + "';";
        ResultSet result = statement.executeQuery(query);
        return result.next();
    }

    public User userLogin(String username, String password) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        if(!username.matches("[A-Za-z0-9]+") || !password.matches("[A-Za-z0-9]+")) return null;
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + passwordHashing(password) + "';";
        ResultSet result = statement.executeQuery(query);
        if(!result.next()) return null;
        return new User(result.getString("username"), result.getString("password"), result.getString("role"),
                result.getString("last_name"), result.getString("first_name"), result.getString("second_name"));
    }
    public ObservableList<User> getUsers(User user) throws SQLException {
        ObservableList<User> users = FXCollections.observableArrayList();
        String username = user.getUsername();
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM users WHERE username <> '" + username + "';";
        ResultSet result = statement.executeQuery(query);
        while(result.next()){
            String name = result.getString("username" );
            String userRole = result.getString("role");
            users.add(new User(name, "0000", userRole, result.getString("last_name"), result.getString("first_name"), result.getString("second_name")));
        }
        return users;
    }
    public void updateRole(User user, String newRole) throws SQLException {
        Statement statement = connection.createStatement();
        String query = "UPDATE users SET role = '" + newRole + "' WHERE username = '" + user.getUsername() + "';";
        statement.executeUpdate(query);
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
}
