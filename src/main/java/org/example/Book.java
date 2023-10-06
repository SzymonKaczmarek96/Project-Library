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
    public String getIsbn() {
        return isbn;
    }
    public String convertBookToSave() {
        return String.format("%s,%s,%s,%s", isbn, author, title, status);
    }
    @Override
    public String toString() {
        return
                "ISBN: " + isbn + '\'' +
                ", AUTHOR: " + author + '\'' +
                ", TITLE: " + title + '\'' +
                ", STATUS: " + status ;
    }
}
