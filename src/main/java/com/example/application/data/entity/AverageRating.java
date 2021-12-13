package com.example.application.data.entity;

public class AverageRating {

    private int restaurantId;
    private String name;
    private double sum;
    private int count;
    private double value;

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }


}
