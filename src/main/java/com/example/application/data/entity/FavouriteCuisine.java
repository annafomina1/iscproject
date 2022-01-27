package com.example.application.data.entity;

/*
This class represents a cuisine. The fields are the cuisine it represents and the number of times the cuisine is rated
or shows up in a list. This class is used in a recommendation algorithm based on cuisine in
RatingService#getRecommendedRestaureantsByCuisine().
Created By: Anna Fomina
Date Created: 2022-01-13
Date Last Edited: 2022-01-13
 */
public class FavouriteCuisine {

    private String cuisine;
    private int count;

    /**
     * Returns the cuisine.
     * @return The cuisine, a String.
     */
    public String getCuisine() {
        return cuisine;
    }

    /**
     * Sets the cuisine
     * @param cuisine String.
     */
    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    /**
     * Returns the number of times this cuisine appears in a list or a user's ratings.
     * @return The number of times the cuisine appears, an integer.
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the number of times this cuisine appears in a list or a user's ratings.
     * @param count Integer.
     */
    public void setCount(int count) {
        this.count = count;
    }

}
