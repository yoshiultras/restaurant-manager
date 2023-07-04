package com.restaurantapp.models;

import java.util.ArrayList;

public class Dishes {
    private ArrayList<Dish> dishes;

    public Dishes() {
        this.dishes = new ArrayList<>();
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
