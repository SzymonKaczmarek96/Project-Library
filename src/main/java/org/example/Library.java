package org.example;

import java.util.ArrayList;
import java.util.List;

public class Library  {

    private List<Book> booksList;

    private BookRepository bookRepository ;

    public Library() {
        this.bookRepository = new FileBookRepository("C:\\Users\\szymo\\IdeaProjects\\library\\src\\main\\resources\\BookRepository.txt");
        this.booksList = bookRepository.getAllBooks();
    }

    public void displayBooks() {
        for (Book book : booksList) {
            System.out.println(book);
        }
    }

    public void addBooksToRepository(Book book) {
        if (!isExist(book)) {
            bookRepository.saveBook(book);
            System.out.println("Added");
        } else {
            System.out.println("Such a book is in the repository");
        }
    }

    public boolean isExist(Book book) {
        for(Book checkExistingBook : bookRepository.getAllBooks()){
            if(checkExistingBook.getIsbn().equals(book.getIsbn())){
                return true;
            }
        };
        return false;
    }



}
