package com.restaurantapp.controllers;

import com.restaurantapp.models.Ingredient;
import com.restaurantapp.models.Order;
import com.restaurantapp.services.ControllerService;
import com.restaurantapp.services.DataService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OrdersController implements Initializable, Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private DataService dataService = DataService.getInstance();
    private ControllerService controllerService = ControllerService.getInstance();
    @FXML
    private TableView<Order> table;
    @FXML
    private TableColumn<Order, String> dateColumn, startColumn, endColumn, waiterColumn, clientColumn, dishColumn;
    @FXML
    private TableColumn<Order, Integer> tableColumn;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("date"));
        startColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("startTime"));
        endColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("endTime"));
        waiterColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("waiter"));
        clientColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("client"));
        dishColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("dishes"));
        tableColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("table"));
        try {
            table.setItems(dataService.getOrders());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void back(ActionEvent event) throws IOException {
        controllerService.changeScene(stage, scene, root, event, "admin.fxml");
    }
}
