package Model;


import Repositories.BookRepository;
import Repositories.FileBookRepository;
import Repositories.FileRentalBookRepository;
import Services.UserApplication;
import Services.UserLoginValidation;

import java.util.List;
import java.util.Scanner;

public class Library {

    private List<Book> booksList;

    private final BookRepository bookRepository;

    private final FileRentalBookRepository fileRentalBookRepository;

    private final UserApplication userApplication;

    private UserLoginValidation userLoginValidation;

    public Library() {
        this.bookRepository = new FileBookRepository();
        this.booksList = bookRepository.getAllBooks();
        this.fileRentalBookRepository = new FileRentalBookRepository();
        this.userApplication = new UserApplication();
        this.userLoginValidation = new UserLoginValidation();
    }

    public void displayBooks() {
        booksList = bookRepository.getAllBooks();
        for (Book book : booksList) {
            System.out.println(book);
        }
    }

    public void checkBookProperties(Book book) {
        if (!exists(book)) {
            if (book.getIsbn().length() == 9) {
                bookRepository.saveBook(book);
                System.out.println("Added");
            } else {
                System.out.println("ISBN number is too short or to long, remember isbn should have 9 numbers");
            }
        } else {
            System.out.println("Such a book is in the repository");
        }
    }

    public boolean exists(Book book) {
        for (Book checkExistingBook : bookRepository.getAllBooks()) {
            if (checkExistingBook.getIsbn().equals(book.getIsbn())) {
                return true;
            }
        }
        return false;
    }

    public void borrow() {
        fileRentalBookRepository.saveBorrowedBooks();
    }

    public void displayRentalBook() {
        fileRentalBookRepository.viewsBorrowedBooks();
    }

    public void returnBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ISBN book which you want to return");
        String ISBNForReturn = scanner.nextLine();
        fileRentalBookRepository.removeBookFromRentalRepository(ISBNForReturn);
    }

    public void searchBooks() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter book name or author or isbn number, to search book");
        String introducedData = scanner.nextLine().toUpperCase();
        bookRepository.searchBook(introducedData);
    }

    public void addBookToRepository() {
        Book book = bookRepository.bookProperties();
        checkBookProperties(book);
    }

    public void regiUser() {
        System.out.println("Register system: ");
        System.out.println("User login must have between 3 and 15 signs");
        System.out.println("Password must have between 5 and 18 signs");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your login for registration: ");
        String login = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        userApplication.registerUser(login, password);
    }

    public void removeBookWithLibrary() {
        Scanner scanner = new Scanner(System.in);
            System.out.println("Login: ");
            String username = scanner.nextLine();
            System.out.println("Password: ");
            String password = scanner.nextLine();
            if(userApplication.loginUser(username,password)){
                if(userLoginValidation.checkUserStatus(username)){
                    System.out.println("Enter the ISBN of the book you are removing from the library");
                    String isbnForRemoveBookWithRepository = scanner.nextLine();
                    fileRentalBookRepository.removeBookFromRentalRepository(isbnForRemoveBookWithRepository);
                    bookRepository.deleteBookWithRepository(isbnForRemoveBookWithRepository);
                }else{
                    System.out.println("You don't have the proper authority");
                };
            }
    }

}
