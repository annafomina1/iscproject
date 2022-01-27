package com.example.application.data.entity;

/*
This class represents the average rating of a restaurant. The fields are the restaurant ID, the name of the restaurant,
the sum of all ratings, the number of ratings it has, and the value of the average rating. The average rating is out
of five "stars". This class is used to calculate the average rating for each restaurant in
RatingService#getAverageRating().
Created By: Anna Fomina
Date Created: 2021-12-13
Date Last Edited: 2021-12-15
 */
public class AverageRating {

    private int restaurantId;
    private String name;
    private double sum;
    private int count;
    private double value;

    /**
     * Returns restaurant ID.
     * @return int restaurant ID
     */
    public int getRestaurantId() {
        return restaurantId;
    }

    /**
     * Sets the restaurant ID.
     * @param restaurantId Integer.
     */
    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    /**
     * Returns the name of the restaurant.
     * @return The name of the restaurant, a String.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the restaurant.
     * @param name String.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the sum of all the ratings for the restaurant.
     * @return Sum of all ratings, a double.
     */
    public double getSum() {
        return sum;
    }

    /**
     * Sets the sum of all ratings for the restaurant.
     * @param sum Double.
     */
    public void setSum(double sum) {
        this.sum = sum;
    }

    /**
     * Returns the number of ratings a restaurant has.
     * @return The number of ratings, an integer.
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the number of ratings a restaurant has.
     * @param count Integer.
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Returns the value of the restaurant's average rating.
     * @return The average rating, a double.
     */
    public double getValue() {
        return value;
    }

    /**
     * Sets the value of a restaurant's average rating.
     * @param value Double.
     */
    public void setValue(double value) {
        this.value = value;
    }

}
