package com.example.application.data.service;

import com.example.application.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

/*
Service contains methods for login and authentication. Connects to the database through the user repository.
Created By: Harper Rapkin
Date Created: 03/01/2022
Date Last Edited: 03/01/2022
 */

@Service
public class AuthService{
    
    /**
    creating a class for AuthException which extends exception
    */
    public class AuthException extends Exception{

    }
    
    //defining a field for the repository
    private UserRepository userRepository;
    
    /**
    connects to the user repository
    @param userRepository which is the UserRepository
    */
    public AuthService(@Autowired UserRepository userRepository){this.userRepository = userRepository;}
    
    /**
    authenticates a user based on their username and password, throws AuthException
    @param username, the String holding the users username
    @param password, the String holding the users password
    */
    public void authenticate(String username, String password) throws AuthException{
        //creating a User object using the username
        User user = userRepository.getByUsername(username);
        //seeing if the username from the User object matches that which the user inputed to the form
        if ((user != null) && user.checkPassword(password)){
        }
        //throwing an exception if the username and password do not match
        else throw new AuthException();
    }
}
