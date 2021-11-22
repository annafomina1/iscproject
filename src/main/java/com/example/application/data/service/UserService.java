package com.example.application.data.service;

import com.example.application.data.entity.Restaurant;
import com.example.application.data.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;
import java.time.LocalDate;
import java.util.List;

@Service
public class UserService extends CrudService<User, Integer> {

    private UserRepository repository;

    public UserService(@Autowired UserRepository repository) {
        this.repository = repository;
    }

    @Override
    protected UserRepository getRepository() {
        return repository;
    }

    public List<User> findAllContacts(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return repository.findAll();
        } else {
            return repository.findAll();
            // return repository.search(stringFilter);
        }
    }

}
