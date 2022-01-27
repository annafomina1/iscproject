package com.example.application.data.service;

import com.example.application.data.entity.Restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;
import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService extends CrudService<Restaurant, Integer> {

    private static RestaurantRepository repository;

    public RestaurantService(@Autowired RestaurantRepository repository) {
        this.repository = repository;
    }

    @Override
    protected RestaurantRepository getRepository() {
        return repository;
    }

    public static List<Restaurant> findAll() {
        return repository.findAll();
    }

    public List<Restaurant> findCuisine(String cuisine){
        List<Restaurant> allRestaurants = repository.findAll();
        List<Restaurant> matches = new ArrayList<>();

        for(int i = 0; i < allRestaurants.size(); i++){
            Restaurant restaurant = allRestaurants.get(i);
            String restaurantCuisine = restaurant.getCuisine();
            if(restaurantCuisine.toLowerCase().equals(cuisine)){
                matches.add(restaurant);
            }


        }

        return matches;
    }


}
