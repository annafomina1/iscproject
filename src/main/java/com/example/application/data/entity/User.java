package com.example.application.data.entity;

import javax.persistence.Entity;
import com.example.application.data.AbstractEntity;
import java.time.LocalDate;

/*
This class represents a user of the website. The fields are the first name, the last name, email address, username,
password, postal code, and date of birth. This class inherits from the AbstractEntity class the id field and the
Java Persistence mapping. This class is used throughout the program.
Created By: Anna Fomina
Date Created: 2021-11-03
Date Last Edited: 2022-01-14
 */
@Entity
public class User extends AbstractEntity {

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String postalCode;
    private LocalDate dateOfBirth;

    /**
     * Empty constructor for a User object.
     */
    public User(){}

    /**
     * Constructor for User object that takes username and password as parameters.
     * @param username String.
     * @param password String.
     */
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    /**
     * Returns the first name of the user.
     * @return First name, a String.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the user.
     * @param firstName String.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name of the user.
     * @return Last name, String.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user.
     * @param lastName String.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the email address of the user.
     * @return Email, a String.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     * @param email String.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the username.
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     * @param username String.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password.
     * @return Password, a String.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     * @param password String.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the user's date of birth.
     * @return The date of birth, a LocalDate.
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the user's date of birth.
     * @param dateOfBirth LocalDate.
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Returns the user's postal code.
     * @return The postal code, a String.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the postal code.
     * @param postalCode String.
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
    created by: Harper Rapkin
    checks the password connected to the username to see if it matches what has been inputed by the user.
    @param p String, the users password
    @return boolean, whether the password matches or not.
    */
    public boolean checkPassword(String p){return p.equals(password);}

}
