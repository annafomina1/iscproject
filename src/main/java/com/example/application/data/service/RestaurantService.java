package com.example.application.data.service;

import com.example.application.data.entity.Restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;
import java.util.ArrayList;
import java.util.List;

/*
This service contains the logic methods for anything related to restaurants. It indirectly connects to the database
through the restaurant repository.
Created By: Anna Fomina, Ava Donaldson
Date Created: 2021-12-13
Date Last Edited: 2022-01-16
 */
@Service
public class RestaurantService extends CrudService<Restaurant, Integer> {

    //connects to the restaurant repository
    private static RestaurantRepository repository;
    public RestaurantService(@Autowired RestaurantRepository repository) {
        this.repository = repository;
    }

    @Override
    protected RestaurantRepository getRepository() {
        return repository;
    }

    /**
     * Returns a list of all restaurants in the database.
     * @return A list of all restaurants, List<Restaurant>.
     */
    public static List<Restaurant> findAll() {
        return repository.findAll();
    }

    /**
     * findCuisine method (created by Ava Donaldson)
     * this is used to search for the user's chosen cuisine
     * @param cuisine, String
     * @return matches, ArrayList
     */
    public List<Restaurant> findCuisine(String cuisine){

        //collects the restaurants from the repository
        List<Restaurant> allRestaurants = repository.findAll();

        //makes a new list to be used for the list of matches
        List<Restaurant> matches = new ArrayList<>();

        //for loop repeats for each restaurant
        for(int i = 0; i < allRestaurants.size(); i++){
            Restaurant restaurant = allRestaurants.get(i);
            String restaurantCuisine = restaurant.getCuisine();

            //checks if the cuisine of each restaurant matches the cuisine the user is searching for
            if(restaurantCuisine.toLowerCase().equals(cuisine)){
                matches.add(restaurant);
            }

        }

        return matches;
    }//end findCuisine method


}
