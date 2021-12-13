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

    private final RatingRepository repository;
    private final RestaurantRepository restaurantRepository;

    public RatingService(@Autowired RatingRepository repository, RestaurantRepository restaurantRepository) {
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    protected RatingRepository getRepository() {
        return repository;
    }

    public List<AverageRating> getAverageRatings() {
        List<Rating> ratings = repository.findAll();
        List<Restaurant> restaurants = restaurantRepository.findAll();

        List<AverageRating> averageRatings = new ArrayList<AverageRating>();

        for (int i = 0; i < ratings.size(); i++) {
            Rating rating = ratings.get(i);

            AverageRating averageRating = null;
            for (int j = 0; j < averageRatings.size(); j++) {
                AverageRating ar = averageRatings.get(j);
                if (ar.getRestaurantId() == rating.getRestaurantId()) {
                    averageRating = ar;
                    break;
                }
            }

            if (averageRating == null) {
                averageRating = new AverageRating();
                averageRatings.add(averageRating);
                averageRating.setRestaurantId(rating.getRestaurantId());

                for (int k = 0; k < restaurants.size(); k++) {
                    Restaurant r = restaurants.get(k);
                    if (r.getId() == averageRating.getRestaurantId()) {
                        averageRating.setName(r.getName());
                        break;
                    }
                }
            }

            averageRating.setSum(averageRating.getSum() + rating.getValue());
            averageRating.setCount(averageRating.getCount() + 1);
        }

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

