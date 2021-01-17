package com.javatechie.springsecurityjwtexample.controllers;

import com.javatechie.springsecurityjwtexample.entities.Book;
import com.javatechie.springsecurityjwtexample.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/addBook")
    public String saveBook(@RequestBody Book book) {
        bookRepository.save(book);
        return "Inserted  :" + book.getId();
    }

    @GetMapping("/findAllBook")
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    @GetMapping("/findAllBook/{id}")
    public Optional<Book> getBook(@PathVariable int id) {
        return bookRepository.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id) {
        bookRepository.deleteById(id);
        return "deleted  :" + id;
    }
}
