package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Book;
import com.example.demo.entities.User;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api")
public class BookController {
  @Autowired
  private  BookRepository bookRepository;
  @Autowired
  private  UserRepository userRepository;

  @GetMapping("/books")
  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }

 @GetMapping("/books/{id}")
public ResponseEntity<?> getBookById(@PathVariable int id) {
    Optional<Book> book = bookRepository.findById(id);
    if (book.isPresent()) {
        return ResponseEntity.ok(book.get());
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
    }
}


  @PostMapping("/books")
  public ResponseEntity<?> addBook(@RequestBody Book book,@AuthenticationPrincipal UserDetails userDetails) {
      Optional<User> userOpt = userRepository.findByEmail(userDetails.getUsername());
    if (userOpt.isEmpty()) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
    }
    book.setUser(userOpt.get());
    Book savedBook = bookRepository.save(book);
    return ResponseEntity.ok(savedBook);
  }

  @PutMapping("/books/{id}")
  public Book updateBook(@PathVariable int id, @RequestBody Book book) {
    Book bookUpdate = bookRepository.findById(id).orElse(null);
    if (bookUpdate != null) {
      bookUpdate.setName(book.getName());
      bookUpdate.setAuthor(book.getAuthor());
      bookUpdate.setSummary(book.getSummary());
      bookRepository.save(bookUpdate);
      return bookUpdate;
    }
    return null;
  }

  @DeleteMapping("/books/{id}")
  public String deleteBook(@PathVariable int id) {
    bookRepository.deleteById(id);
    return "Book deleted successfully";
  }

  @GetMapping("/books/search/{name}")
  public List<Book> findBookByName(@PathVariable String name) {
    return bookRepository.findAll().stream().filter(e -> e.getName().equalsIgnoreCase(name)).toList();
  }
@GetMapping("/books/my")
public ResponseEntity<?> getMyBooks(@AuthenticationPrincipal UserDetails userDetails) {
    Optional<User> userOpt = userRepository.findByName(userDetails.getUsername());
    if (userOpt.isEmpty()) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
    }

    List<Book> books = bookRepository.findByUser(userOpt.get());
    return ResponseEntity.ok(books);
}




}
