package com.example.application.data.entity;

import javax.persistence.Entity;
import com.example.application.data.AbstractEntity;

/*
This class represents one rating for a specific restaurant. The fields are the restaurant ID, the user ID, and the value
of the rating (1-5). This class inherits from the AbstractEntity class the id field and the Java Persistence mapping.
This class is used throughout the program.
Created By: Anna Fomina
Date Created: 2021-11-12
Date Last Edited: 2022-01-14
 */
@Entity
public class Rating extends AbstractEntity{

    // fields: restaurant id, user id, value of rating (int 1-5)
    private int restaurantId;
    private int userId;
    private int value;


    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
