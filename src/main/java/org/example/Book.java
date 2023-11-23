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

    public Book(String isbn, String author, String title) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.status =Status.BORROWED;
    }

    public Book(String isbn) {
        this.isbn = isbn;
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
                isbn + ',' +
                author + ',' +
                title + ',' +
                status  ;
    }
}
