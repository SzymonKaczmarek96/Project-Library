package org.example;

import java.util.ArrayList;
import java.util.List;

public class Library  {

    private List<Book> booksList;

    private BookRepository bookRepository ;

    public Library() {
        this.bookRepository = new FileBookRepository();
        this.booksList = bookRepository.getAllBooks();
    }

    public void displayBooks() {
        for (Book book : booksList) {
            System.out.println(book);
        }
    }

    public void addBooksToReposiroty(Book book) {
        if (!isExist(book)) {
            bookRepository.saveBook(book);
            System.out.println("Added");
        } else {
            System.out.println("");
        }
    }

    public boolean isExist(Book book) {

        return true;
    }

}
