package org.example;

import BorrowBook.FileRentalBookRepository;

import java.util.List;

public class Library  {

    private List<Book> booksList;

    private BookRepository bookRepository ;

    private FileRentalBookRepository fileRentalBookRepository;

    public Library() {
        this.bookRepository = new FileBookRepository("C:\\Users\\szymo\\IdeaProjects\\library\\src\\main\\resources\\BookRepository.txt");
        this.booksList = bookRepository.getAllBooks();
        this.fileRentalBookRepository = new FileRentalBookRepository();
    }

    public void displayBooks() {
        for (Book book : booksList) {
            System.out.println(book);
        }
    }

    public void addBookToRepository(Book book) {
        if (!exists(book)) {
            bookRepository.saveBook(book);
            System.out.println("Added");
        } else {
            System.out.println("Such a book is in the repository");
        }
    }

    public boolean exists(Book book) {
        for(Book checkExistingBook : bookRepository.getAllBooks()){
            if(checkExistingBook.getIsbn().equals(book.getIsbn())){
                return true;
            }
        };
        return false;
    }

    public void borrow() {
      fileRentalBookRepository.saveBorrowedBooks();
    }

    public void displayRentalBook() {
        fileRentalBookRepository.viewsBorrowedBooks();
    }




}
