package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entities.Book;
import com.example.demo.entities.User;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByUser(User user);
}
