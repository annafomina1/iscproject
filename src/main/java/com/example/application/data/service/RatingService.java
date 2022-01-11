package com.example.application.data.service;

import com.example.application.data.entity.AverageRating;
import com.example.application.data.entity.Rating;
import com.example.application.data.entity.Restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class RatingService extends CrudService<Rating, Integer>{

    //rating and restaurant repository variables
    private final RatingRepository repository;
    private final RestaurantRepository restaurantRepository;

    /**
     * Rating Service constructor, sets repositories to respective variables
     * @param repository Rating repository, main one for this service class
     * @param restaurantRepository Restaurant repository
     */
    public RatingService(@Autowired RatingRepository repository, RestaurantRepository restaurantRepository) {
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    /**
     * Returns the Rating repository
     */
    protected RatingRepository getRepository() {
        return repository;
    }

    /**
     * Calculates the average rating for each restaurant using all ratings from the Rating repository
     * @return List<AverageRating>, list of average ratings, one for each restaurant
     */
    public List<AverageRating> getAverageRatings() {
        //creates a list of all ratings and a list of all restaurants, finding all entries in the respective repositories
        List<Rating> ratings = repository.findAll();
        List<Restaurant> restaurants = restaurantRepository.findAll();

        //creates a new list to store the average ratings
        List<AverageRating> averageRatings = new ArrayList<AverageRating>();

        //iterates through each rating
        for (int i = 0; i < ratings.size(); i++) {
            //creates a rating object and assigns a rating from the list of ratings to it
            Rating rating = ratings.get(i);
            //creates an average rating object, null for now
            AverageRating averageRating = null;

            //iterates through all average ratings in the averageRatings list
            for (int j = 0; j < averageRatings.size(); j++) {
                    //assigns the average rating to an AverageRating object
                    AverageRating ar = averageRatings.get(j);
                /*if the rating from the averageRatings list is from the same restaurant (has the same restaurantID)
                as the rating from the ratings list, assigns the rating to the averageRating object and breaks out of
                the inner for loop
                */
                    if (ar.getRestaurantId() == rating.getRestaurantId()) {
                    averageRating = ar;
                    break;
                }
            }//close inner for loop

            //if the averageRating varibale is still null, meaning that
            if (averageRating == null) {
                //creates new averageRating object
                averageRating = new AverageRating();
                //adds it to the list of average ratings
                averageRatings.add(averageRating);
                //sets the restaurantID of the rating to the restaurantID of the rating before it ???
                averageRating.setRestaurantId(rating.getRestaurantId());

                //iterates through list of all restaurants
                for (int k = 0; k < restaurants.size(); k++) {
                    //creates a Restaurant abject and assigns it to the restaurant from the list
                    Restaurant r = restaurants.get(k);
                    //if the
                    if (r.getId() == averageRating.getRestaurantId()) {
                        averageRating.setName(r.getName());
                        break;
                    }
                }
            }

            averageRating.setSum(averageRating.getSum() + rating.getValue());
            averageRating.setCount(averageRating.getCount() + 1);
        }//close main for loop

        for (int j = 0; j < averageRatings.size(); j++) {
            AverageRating ar = averageRatings.get(j);
            double average = ar.getSum() / ar.getCount();
            double roundedAverage = Math.round(10 * average) / 10.0;
            ar.setValue(roundedAverage);
        }

        Comparator<AverageRating> comparator = Comparator.comparingDouble(AverageRating::getValue).reversed();
        averageRatings.sort(comparator);

        return averageRatings;
    }

}
