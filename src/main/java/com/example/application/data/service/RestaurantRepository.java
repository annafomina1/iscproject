package com.example.application.data.service;

import com.example.application.data.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

/*
This repository connects the restaurant table in the mySQL database.
 */
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

}