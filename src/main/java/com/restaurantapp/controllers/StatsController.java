package com.restaurantapp.controllers;

import com.restaurantapp.models.Dish;
import com.restaurantapp.services.ControllerService;
import com.restaurantapp.services.DataService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class StatsController implements Initializable, Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label bestLabel1, bestLabel2, bestLabel3, worstLabel1, worstLabel2, worstLabel3;
    private final ControllerService controllerService = ControllerService.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            List<Dish> popular = DataService.getPopularDishes();
            List<Dish> unpopular = DataService.getUnpopularDishes();
            bestLabel1.setText(popular.get(0).getName());
            bestLabel2.setText(popular.get(1).getName());
            bestLabel3.setText(popular.get(2).getName());
            worstLabel1.setText(unpopular.get(0).getName());
            worstLabel2.setText(unpopular.get(1).getName());
            worstLabel3.setText(unpopular.get(2).getName());
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void back(ActionEvent event) throws IOException {
        controllerService.changeScene(stage, scene, root, event, "admin.fxml");
    }
}
