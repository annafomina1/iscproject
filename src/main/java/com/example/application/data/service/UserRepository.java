package com.example.application.data.service;

import com.example.application.data.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;

public interface UserRepository extends JpaRepository<User, Integer> {

}