package com.example.application.data.service;

import com.example.application.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/*
This repository connects the rating table in the mySQL database.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

  User getByUsername(String username);
}
