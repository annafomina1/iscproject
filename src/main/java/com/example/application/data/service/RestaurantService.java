package com.example.application.data.service;

import com.example.application.data.entity.Restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;
import java.time.LocalDate;
import java.util.List;

@Service
public class RestaurantService extends CrudService<Restaurant, Integer> {

    private RestaurantRepository repository;

    public RestaurantService(@Autowired RestaurantRepository repository) {
        this.repository = repository;
    }

    @Override
    protected RestaurantRepository getRepository() {
        return repository;
    }

    public List<Restaurant> findAll() {
        return repository.findAll();
    }

    //search method for restaurant search bar similar to above method

}
