package com.restaurantapp.models;

public class Ingredient {
    private String name;
    private String unit;
    private double amount;

    public Ingredient(String name, String unit, double amount) {
        this.name = name;
        this.unit = unit;
        this.amount = amount;
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

    public String getAmount() {
        return amount + " " + unit;
    }

    public void setAmount(int amount_in_stock) {
        this.amount = amount_in_stock;
    }
}
