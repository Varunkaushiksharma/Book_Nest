package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Book;
import com.example.demo.entities.Library;
import com.example.demo.entities.User;

public interface LibraryRepository extends JpaRepository<Library, Integer> {
    List<Library> findByUser(User user);
    boolean existsByUserAndBook(User user, Book book);
    Optional<Library> findByUserAndBook(User user, Book book);
}
