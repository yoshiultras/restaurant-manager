package com.restaurantapp.models;

public class Ingredient {
    private String name;
    private String unit;
    private double amount_in_stock;

    public Ingredient(String name, String unit, double amount_in_stock) {
        this.name = name;
        this.unit = unit;
        this.amount_in_stock = amount_in_stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getAmount_in_stock() {
        return amount_in_stock;
    }

    public void setAmount_in_stock(int amount_in_stock) {
        this.amount_in_stock = amount_in_stock;
    }
}
