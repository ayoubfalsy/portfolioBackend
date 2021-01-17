package com.javatechie.springsecurityjwtexample.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Book")
public class Book {
    @Id
    private int id;
    private String bookName;
    private String authorName;

    public int getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthorName() {
        return authorName;
    }
}
