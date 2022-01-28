package com.example.application.data.service;


import com.example.application.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;
import java.util.List;
/*
This service contains the logic methods for anything related to users. It indirectly connects to the database
through the user repository.
Created By: Anna Fomina
Date Created: 2021-12-03
Date Last Edited: 2022-01-15
 */
@Service
public class UserService extends CrudService<User, Integer> {

    //connects to user repository
    private UserRepository repository;

    public UserService(@Autowired UserRepository repository) {
        this.repository = repository;
    }

    @Override
    protected UserRepository getRepository() {
        return repository;
    }

    /**
     * Returns a list of all users in the database using the user repository.
     * @return All users, List<User>.
     */
    public List<User> findAll() {
        return repository.findAll();
    }

    /**
     * Finds a user from the database by username.
     * @param username The username to be found, a String.
     * @return A User object or null depending on if the user was found.
     */
    public User findByUserName(String username) {
        List<User> users = repository.findAll();
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }



}
