package com.example.application.data.entity;

import javax.persistence.Entity;

import com.example.application.data.AbstractEntity;
import java.time.LocalDate;

@Entity
public class Restaurant extends AbstractEntity {

    private String name;
    private String address;
    private String postalCode;
    private String cuisine;
    private String location;
    private String cost;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCuisine() {
        return cuisine;
    }
    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {this.location = location;}

    public String getCost() {
        return cost;
    }
    public void setCost(String cost) {
        this.cost = cost;
    }



}
