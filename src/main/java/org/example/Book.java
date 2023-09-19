package org.example;

public class Book {

    private final String isbn;

    private String author;

    private String title;

    private Status status;

    public Book(String isbn, String author, String title, Status status) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.status = status;
    }
}
