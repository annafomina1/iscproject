package com.example.application.data.entity;
import javax.persistence.Entity;

import com.example.application.data.AbstractEntity;
import java.util.Date;

@Entity
public class Rating extends AbstractEntity{

    private int restaurantId;
    private int userId;
    private int value;
    private String comment;


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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
