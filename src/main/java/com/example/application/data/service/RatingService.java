package com.example.application.data.service;

import com.example.application.data.entity.AverageRating;
import com.example.application.data.entity.FavouriteCuisine;
import com.example.application.data.entity.Rating;
import com.example.application.data.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/*
This service contains the logic methods for anything related to restaurant ratings. It indirectly connects to the database
through the rating and restaurant repositories.
Created By: Anna Fomina
Date Created: 2021-12-13
Date Last Edited: 2022-01-15
 */
@Service
public class RatingService extends CrudService<Rating, Integer> {

    private final RatingRepository repository;
    private final RestaurantRepository restaurantRepository;

    /**
     * Connects to the restaurant and rating repositories.
     * @param repository The rating repository,
     * @param restaurantRepository  The restaurant repository.
     */
    public RatingService(@Autowired RatingRepository repository, RestaurantRepository restaurantRepository) {
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
    }


    /**
     * Returns the Rating repository.
     */
    @Override
    protected RatingRepository getRepository() {
        return repository;
    }

    /**
     * Calculates and returns the average rating for each restaurant using all ratings from the Rating repository.
     * @return List<AverageRating>, list of average ratings, one for each restaurant.
     */
    public List<AverageRating> getAverageRatings() {
        //creates a list of all ratings and a list of all restaurants
        List<Rating> ratings = repository.findAll();
        List<Restaurant> restaurants = restaurantRepository.findAll();

        //creates a new list to store the average ratings
        List<AverageRating> averageRatings = new ArrayList<AverageRating>();

        //iterates through each rating
        for (int i = 0; i < ratings.size(); i++) {
            //creates a rating object to hold the rating
            Rating rating = ratings.get(i);
            //creates an average rating object, null for now
            AverageRating averageRating = null;

            //iterates through all average ratings in the averageRatings list
            for (int j = 0; j < averageRatings.size(); j++) {
                //assigns the average rating to a new AverageRating object
                AverageRating ar = averageRatings.get(j);
                /*if the rating from the averageRatings list is from the same restaurant (has the same restaurantID)
                as the rating from the ratings list, assigns the rating to the averageRating object (the one that was
                previously null) and breaks out of the inner for loop
                */
                if (ar.getRestaurantId() == rating.getRestaurantId()) {
                    averageRating = ar;
                    break;
                }
            }//close inner for loop

            /*if the averageRating variable is still null, meaning that the rating from the averageRatings list is not
            for the same restaurant as the one in the ratings list, this restaurant does not yet have an average rating
            associated with it, so we make a new one. Using the restaurant ID from the original rating in the main for loop.
             */
            if (averageRating == null) {
                //assigns it to a new averageRating object
                averageRating = new AverageRating();
                //adds it to the list of average ratings
                averageRatings.add(averageRating);
                //sets the restaurant ID of the average rating to the restaurant ID of the rating from the ratings list
                averageRating.setRestaurantId(rating.getRestaurantId());

                //iterates through list of all restaurants
                for (int k = 0; k < restaurants.size(); k++) {
                    //creates a Restaurant abject and assigns it to the restaurant from the list
                    Restaurant r = restaurants.get(k);
                    /*when it finds the restaurant that has the same restaurant ID as the averageRating, it gets the
                    restaurant name and sets the name of the averageRating to that.
                     */
                    if (r.getId() == averageRating.getRestaurantId()) {
                        averageRating.setName(r.getName());
                        break; //breaks out of for loop when name is set.
                    }//close if statement
                }//close restaurant name for loop
            }//close averageRating == null for loop

            //adds the value of the rating to the sum of ratings
            averageRating.setSum(averageRating.getSum() + rating.getValue());
            //adds 1 to the number of ratings count
            averageRating.setCount(averageRating.getCount() + 1);

        }//close main for loop

        //iterates though each average rating object in the list
        for (int j = 0; j < averageRatings.size(); j++) {
            //assigns to an AverageRating object
            AverageRating ar = averageRatings.get(j);
            //calculates the average by dividing the sum by the count
            double average = ar.getSum() / ar.getCount();
            //counts the average to one tenth
            double roundedAverage = Math.round(10 * average) / 10.0;
            //sets the value of the average rating
            ar.setValue(roundedAverage);
        }

        //users a comparator to sort the AverageRatings from the highest average rating value to the lowest
        Comparator<AverageRating> comparator = Comparator.comparingDouble(AverageRating::getValue).reversed();
        averageRatings.sort(comparator);

        // returns the list of AverageRating objects, one for each restaurant with a rating.
        return averageRatings;
    }

    /**
     * Returns a list of recommended restaurants for a user. Recommendations are based on restaurants with the same
     * cuisine as other restaurants the user has rated.
     * @param userId User ID of the user for whom the restaurants are being recommended, integer.
     * @return A list of Restaurant objects.
     */
    public List<Restaurant> getRecommendedRestaurantsByCuisine(int userId) {
        //creates a list of all ratings and a list of all restaurants from the respective repositories
        List<Rating> allRatings = repository.findAll();
        List<Restaurant> restaurants = restaurantRepository.findAll();

        //creates empty list that will hold all the user's rated restaurants
        List<Restaurant> ratedRestaurants = new ArrayList<>();

        //iterates through the allRatings list
        for (int i = 0; i < allRatings.size(); i++) {
            //creates a Rating object to hold the current rating
            Rating rating = allRatings.get(i);
            //checks if the rating was made by the same user as the user from the parameter
            if (rating.getUserId() == userId) {
                //stores the restaurant ID of the rated restaurant in an int variable
                int ratedRestaurantId = rating.getRestaurantId();

                //iterates through the list of restaurant
                for (int j = 0; j < restaurants.size(); j++) {
                    Restaurant restaurant = restaurants.get(j);
                    //checks if each restaurant has the same ID and the rated restaurant
                    if (restaurant.getId() == ratedRestaurantId) {
                        //adds the correct restaurant object to the ratedRestaurants list
                        ratedRestaurants.add(restaurant);
                        break; //break for loop when restaurant found
                    }//close if
                }//close restaurant for loop
            }//close user ID check if
        }// close allRatings for loop

        //creates a new list that will hold the list of cuisines
        List<FavouriteCuisine> favouriteCuisineList = new ArrayList<>();

        //iterates through the list of the user's rated restaurants
        for (int i = 0; i < ratedRestaurants.size(); i++) {
            Restaurant ratedRestaurant = ratedRestaurants.get(i);
            //gets the cuisine of the rated restaurant
            String restaurantCuisine = ratedRestaurant.getCuisine().trim();

            //creates an empty favouriteCuisine object, null for now
            FavouriteCuisine favouriteCuisine = null;
            //iterates through the list of favouriteCuisines
            for (int j = 0; j < favouriteCuisineList.size(); j++) {
                FavouriteCuisine favouriteCuisineItem = favouriteCuisineList.get(j);
                //checks if the cuisine of the item from the list is the same as the cuisine of the rated restaurant
                if (favouriteCuisineItem.getCuisine().equals(restaurantCuisine)) {
                    //sets the previously null favouriteCuisine to the value of the item from the list
                    favouriteCuisine = favouriteCuisineItem;
                    break;
                }//close if
            }//close favouriteCuisineList for loop

            //check if the favouriteCuisine is still null
            if (favouriteCuisine == null) {
                //creates a new FavouriteCuisine object with the same name
                favouriteCuisine = new FavouriteCuisine();
                //sets the cuisine of the object to the cuisine of the user's rated restaurant
                favouriteCuisine.setCuisine(restaurantCuisine);
                //adds the object to the list of favourite cuisines
                favouriteCuisineList.add(favouriteCuisine);
            }

            //adds one to the count of that cuisine
            favouriteCuisine.setCount(favouriteCuisine.getCount() + 1);
        }//close rated restaurants for loop

        //uses a comparator to sort the FavouriteCuisine objects the list from highest to lowest count
        Comparator<FavouriteCuisine> comparator = Comparator.comparingInt(FavouriteCuisine::getCount).reversed();
        favouriteCuisineList.sort(comparator);

        //creates new empty list for recommended restaurants
        List<Restaurant> recommendedRestaurants = new ArrayList<>();

        //iterates through users rated restaurants and removes all rates restaurants from the all restaurants list
        for (int i = 0; i < ratedRestaurants.size(); i++) {
            Restaurant ratedRestaurant = ratedRestaurants.get(i);
            restaurants.remove(ratedRestaurant);
        }

        //iterates through the list of FavouriteCuisines
        for (int i = 0; i < favouriteCuisineList.size(); i++) {
            FavouriteCuisine favoriteCuisine = favouriteCuisineList.get(i);
            //gets the cuisine
            String cuisine = favoriteCuisine.getCuisine();

            //iterates through the list of restaurant, ones not rated by the user remaining
            for (int j = 0; j < restaurants.size(); j++) {
                Restaurant restaurant = restaurants.get(j);
                //checks if that restaurant has the same cuisine as the FavouriteCuisine object
                if (restaurant.getCuisine().trim().equals(cuisine)) {
                    //if so, adds it the list of recommended restaurants
                    recommendedRestaurants.add(restaurant);
                }//close if
            }//close restaurants for loop
        }//close favouriteCuisineList for loop

        //returns the list of recommended restaurants
        return recommendedRestaurants;
    }

    /**
     * Returns a list of recommended restaurants for a user. Recommendations are all the restaurants that the user
     * has not yet rated.
     * @param userId User ID of the user for whom the restaurants are being recommended, integer.
     * @return A list of Restaurant objects.
     */
    public List<Restaurant> getRecommendedRestaurantsByRating(int userId) {
        //creates a list of all ratings and a list of all restaurants from the respective repositories
        List<Rating> allRatings = repository.findAll();
        List<Restaurant> restaurants = restaurantRepository.findAll();
        //creates a list of AverageRatings using the first method in this service.
        List<AverageRating> averageRatings = getAverageRatings();

        //creates an empty list of ID's for the restaurants the user has rated
        List<Integer> ratedRestaurantIds = new ArrayList<>();

        //iterates through all ratings
        for (int i = 0; i < allRatings.size(); i++) {
            Rating rating = allRatings.get(i);
            //checks if the user rated each rating by comparing user IDs
            if (rating.getUserId() == userId) {
                int ratedRestaurantId = rating.getRestaurantId();
                //adds the ID of the rated restaurant to the list
                ratedRestaurantIds.add(ratedRestaurantId);
            }
        }

        //creates empty list to hold recommended restaurants
        List<Restaurant> recommendedRestaurants = new ArrayList<>();

        //iterates through list of average ratings
        for (int i = 0; i < averageRatings.size(); i++) {
            AverageRating rating = averageRatings.get(i);
            //gets the restaurant ID of that rating's restaurant
            int ratingRestaurantId = rating.getRestaurantId();
            //checks if this rating's restaurant is in the list of the user's rated restaurants
            boolean isRated = ratedRestaurantIds.contains(ratingRestaurantId);
            //if the user has not rated this restaurant...
            if (!isRated) {
                //iterates through all restaurants
                for (int j = 0; j < restaurants.size(); j++) {
                    Restaurant restaurant = restaurants.get(j);
                    int restaurantId = restaurant.getId();
                    //if the restaurant is the one from the rating, add it to the list of recommendations
                    if (restaurantId == ratingRestaurantId) {
                        recommendedRestaurants.add(restaurant);
                        break;
                    }//close ID check if
                }//close all restaurants for loop
            }//close not rated if
        }//close averageRatings list for loop

        //returns the list of recommended restaurants
        return recommendedRestaurants;
    }

}

