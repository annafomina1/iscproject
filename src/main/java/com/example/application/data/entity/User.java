package com.example.application.data.entity;

import javax.persistence.Entity;

import com.example.application.data.AbstractEntity;
import java.time.LocalDate;

/**
 * This class represents a user of the website
 */
@Entity
public class User extends AbstractEntity {

    //fields: first name, last name, email address, username, password, postal code, date of birth
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String postalCode;
    private LocalDate dateOfBirth;
    
    public User(){}
    
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
    
    public boolean checkPassword(String p){return p.equals(password);}

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }


}
