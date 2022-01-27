package com.example.application.data.service;

import com.example.application.data.entity.Rating;

import org.springframework.data.jpa.repository.JpaRepository;
/*
This repository connects the rating table in the mySQL database.
 */
public interface RatingRepository extends JpaRepository<Rating, Integer> {

}