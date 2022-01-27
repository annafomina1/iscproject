package com.example.application.data.entity;

import javax.persistence.Entity;
import com.example.application.data.AbstractEntity;

/*
This class represents a restaurant. The fields are the name of the restaurant, the address, the postal
code, the cuisine, the location (city and province), and the cost ($-$$$$). This class inherits from the AbstractEntity
class the id field and the Java Persistence mapping. This class is used throughout the program.
Created By: Anna Fomina
Date Created: 2021-11-03
Date Last Edited: 2022-01-23
 */
@Entity
public class Restaurant extends AbstractEntity {

    private String name;
    private String address;
    private String postalCode;
    private String cuisine;
    private String location;
    private String cost;

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
     * Returns the street address of the restaurant. City and province are excluded in this.
     * @return The address of the restaurant, a String.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the street address of the restaurant. City and province are excluded in this.
     * @param address String.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the postal code of the restaurant.
     * @return The postal code, a String.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the postal code of the restaurant.
     * @param postalCode String.
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Returns the cuisine of the restaurant.
     * @return The cuisine, a String.
     */
    public String getCuisine() {
        return cuisine;
    }

    /**
     * Sets the cuisine of the restaurant.
     * @param cuisine String.
     */
    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    /**
     * Returns the location of the restaurant in the format City, Province Abbreviated. For example, Toronto, ON.
     * @return The location, a String.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the restaurant in the format City, Province Abbreviated. For example, Toronto, ON.
     * @param location String.
     */
    public void setLocation(String location) {this.location = location;}

    /**
     * Returns the cost range of the restaurant as a number of "$" symbols between 1-4. $ is the lowest price range and
     * $$$$ is the most expensive.
     * @return The cost range, a String.
     */
    public String getCost() {
        return cost;
    }

    /**
     * Sets the cost range of the restaurant as a number of "$" symbols between 1-4. $ is the lowest price range and
     * $$$$ is the most expensive.
     * @param cost String.
     */
    public void setCost(String cost) {
        this.cost = cost;
    }

}
