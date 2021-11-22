package com.example.application.data.service;

import com.example.application.data.entity.Restaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

}