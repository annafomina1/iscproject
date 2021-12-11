package com.example.application.data.service;

import com.example.application.data.entity.Rating;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

}