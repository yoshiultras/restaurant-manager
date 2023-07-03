package com.restaurantapp.controllers;

import com.restaurantapp.models.User;
import com.restaurantapp.services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AccessController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private User user = User.getUser();
    @FXML
    private Button backButton;
    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, String> loginColumn;
    @FXML
    private TableColumn<User, Integer> roleColumn;
    UserService userService = UserService.getInstance();
    public void back(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("admin.fxml"));
        root = loader.load();
        AdminController adminController = loader.getController();
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("role"));
        roleColumn.setEditable(true);
        try {
            table.setItems(userService.getUsersLowerRole(user));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}