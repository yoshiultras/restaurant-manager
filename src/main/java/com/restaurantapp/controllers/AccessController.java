package com.restaurantapp.controllers;

import com.restaurantapp.models.User;
import com.restaurantapp.services.ControllerService;
import com.restaurantapp.services.DataService;
import com.restaurantapp.services.UserService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AccessController implements Initializable, Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private final User user = User.getUser();
    private User selectedUser;
    @FXML
    private Label errorLabel, roleLabel, userLabel;
    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, String> loginColumn, nameColumn;
    @FXML
    private TableColumn<User, String> roleColumn;
    @FXML
    private ChoiceBox<String> roleChoiceBox;
    private final UserService userService = UserService.getInstance();
    private final ControllerService controllerService = ControllerService.getInstance();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roleLabel.setText(user.getRole());
        errorLabel.setText("");
        roleChoiceBox.setItems(FXCollections.observableArrayList("Нет роли", "Складской менеджер", "Менеджер ресторана", "Директор"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<User, String>("role"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("fullName"));
        TableView.TableViewSelectionModel<User> selectionModel = table.getSelectionModel();
        selectionModel.selectedItemProperty().addListener((val, oldVal, newVal) -> {
            if(newVal != null) {
                selectedUser = newVal;
                userLabel.setText(selectedUser.getUsername());
            }

        });
        try {
            table.setItems(DataService.getUsers(user));
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void delete(ActionEvent event) throws SQLException {
        if(selectedUser == null) {
            errorLabel.setTextFill(new Color(1, 0, 0, 1));
            errorLabel.setText("Не выбран пользователь");
            return;
        }
        userService.deleteUser(selectedUser);
        errorLabel.setText("Пользователь " + selectedUser.getUsername() + " удален");
        ObservableList<User> newList = table.getItems();
        newList.remove(selectedUser);
        table.setItems(newList);


    }
    public void changeRole(ActionEvent event) throws SQLException {
        if(selectedUser == null) {
            errorLabel.setTextFill(new Color(1, 0, 0, 1));
            errorLabel.setText("Не выбран пользователь");
            return;
        }
        String role = roleChoiceBox.getValue();

        if(role == null) {
            errorLabel.setTextFill(new Color(1, 0, 0, 1));
            errorLabel.setText("Не выбрана роль");
            return;
        }
        selectedUser.setRole(role);
        userService.updateRole(selectedUser, role);
        errorLabel.setTextFill(new Color(0, 0.5, 0, 1));
        errorLabel.setText("Пользователю " + user.getUsername() + " был выдан уровень доступа " + role);
    }
    public void back(ActionEvent event) throws IOException {
        controllerService.changeScene(stage, scene, root, event, "admin.fxml");
    }
    private boolean acceptRole(String role) {
        return Integer.parseInt(user.getRole()) >= Integer.parseInt(role);
    }
}