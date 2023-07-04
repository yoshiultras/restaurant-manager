package com.restaurantapp.models;

public class Order {
    private Dishes dishes;
    private String date;
    private String startTime;
    private String endTime;
    private String waiter;
    private String client;
    private int table;

    public Order(Dishes dishes, String date, String startTime, String endTime, String waiter, String client, int table) {
        this.dishes = dishes;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.waiter = waiter;
        this.client = client;
        this.table = table;
    }

    public Order(Dishes dishes, String date, String startTime, String endTime, String waiter, int table) {
        this.dishes = dishes;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.waiter = waiter;
        this.table = table;
    }

    public Dishes getDishes() {
        return dishes;
    }

    public void setDishes(Dishes dishes) {
        this.dishes = dishes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getWaiter() {
        return waiter;
    }

    public void setWaiter(String waiter) {
        this.waiter = waiter;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }
}
