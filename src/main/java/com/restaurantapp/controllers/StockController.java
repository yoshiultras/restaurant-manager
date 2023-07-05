package com.restaurantapp.controllers;

import com.restaurantapp.models.Ingredient;
import com.restaurantapp.services.ControllerService;
import com.restaurantapp.data.DataStorage;
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

public class StockController implements Initializable, Controller {
    private Parent root;
    private final ControllerService controllerService = ControllerService.getInstance();
    @FXML
    private TableView<Ingredient> table;
    @FXML
    private TableColumn<Ingredient, String> nameColumn, unitColumn;
    @FXML
    private TableColumn<Ingredient, String> amountColumn;

    public void back(ActionEvent event) throws IOException {
        controllerService.changeScene(root, event, "admin.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("name"));
        unitColumn.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("unit"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("amount"));
        try {
            table.setItems(DataStorage.getIngredients());
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
