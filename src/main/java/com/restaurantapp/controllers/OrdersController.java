package com.restaurantapp.controllers;

import com.restaurantapp.models.*;
import com.restaurantapp.services.ControllerService;
import com.restaurantapp.services.DataService;
import com.restaurantapp.services.OrderService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class OrdersController implements Initializable, Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private final ControllerService controllerService = ControllerService.getInstance();
    private final OrderService orderService = OrderService.getInstance();
    private ObservableList<Dish> addedDishes = FXCollections.observableArrayList();
    @FXML
    private TableView<Order> table;
    @FXML
    private TableColumn<Order, String> dateColumn, startColumn, endColumn, waiterColumn, clientColumn, dishColumn;
    @FXML
    private TableColumn<Order, Integer> tableColumn;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ChoiceBox<Table> tablePicker;
    @FXML
    private ChoiceBox<Waiter> waiterPicker;
    @FXML
    private ChoiceBox<Client> clientPicker;
    @FXML
    private ChoiceBox<Dish> dishPicker;
    @FXML
    private Label errorLabel;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorLabel.setVisible(false);
        dateColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("date"));
        startColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("startTime"));
        endColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("endTime"));
        waiterColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("waiter"));
        clientColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("client"));
        dishColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("dishes"));
        tableColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("table"));
        try {
            table.setItems(DataService.getOrders());
            tablePicker.setItems(DataService.getTables());
            waiterPicker.setItems(DataService.getWaiters());
            clientPicker.setItems(DataService.getClients());
            dishPicker.setItems(DataService.getDishes());
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void addDish(ActionEvent event) {
        addedDishes.add(dishPicker.getValue());
    }
    public void addOrder(ActionEvent event) throws SQLException {
        LocalDate date = datePicker.getValue();
        Table table1 = tablePicker.getValue();
        Waiter waiter = waiterPicker.getValue();
        Client client = clientPicker.getValue();
        if(date == null || table1 == null || waiter == null || client == null || addedDishes.isEmpty()) {
            errorLabel.setVisible(true);
        }
        errorLabel.setVisible(false);
        orderService.addOrder(date, table1, waiter, client, addedDishes);
        addedDishes.clear();
    }
    public void back(ActionEvent event) throws IOException {
        controllerService.changeScene(stage, scene, root, event, "admin.fxml");
    }
}
