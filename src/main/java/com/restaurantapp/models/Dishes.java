package com.restaurantapp.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Dishes {
    private ObservableList<Dish> dishes;

    public Dishes() {
        this.dishes = FXCollections.observableArrayList();
    }

    public Dishes(ObservableList<Dish> dishes) {
        this.dishes = dishes;
    }

    public void add(Dish dish) {
        dishes.add(dish);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Dish dish : dishes) {
            sb.append(dish).append("\n");
        }
        return sb.toString();
    }
}
