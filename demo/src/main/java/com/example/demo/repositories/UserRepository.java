package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.User;


public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByName(String name);
  Optional<User> findById(int id);
  Optional<User> findByEmail(String email);
  
}
