package com.example.application.data.service;

import com.example.application.data.entity.Rating;
import com.example.application.data.entity.Restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;
import java.time.LocalDate;
import java.util.List;

@Service
public class RatingService extends CrudService<Rating, Integer>{

    private RatingRepository repository;

    public RatingService(@Autowired RatingRepository repository) {
        this.repository = repository;
    }

    @Override
    protected RatingRepository getRepository() {
        return repository;
    }

    public List<Rating> findAllContacts(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return repository.findAll();
        } else {
            return repository.findAll();
            // return repository.search(stringFilter);
        }
    }
}
