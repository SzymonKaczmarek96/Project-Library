package Services;

import Excaptions.InvalidBookException;
import Model.Book;
import Model.Library;
import Model.Status;
import Repositories.FileBookRepository;
import Repositories.FileRentalBookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookSelection {

    private final Library library;

    private final List<String> borrowedBook;

    private final UpdateBookStatus updateBookStatus;

    private final FileBookRepository fileBookRepository;

    private final FileRentalBookRepository fileRentalBookRepository;

    private boolean bookAvailable;

    public BookSelection() {
        this.library = new Library();
        this.fileBookRepository = new FileBookRepository();
        this.borrowedBook = borrowBook();
        this.updateBookStatus = new UpdateBookStatus();
        this.fileRentalBookRepository = new FileRentalBookRepository();
        this.bookAvailable = false;

    }

    public Book attemptBookBorrowing() throws InvalidBookException {

        String isbn = borrowedBook.get(0);
        String author = borrowedBook.get(1);
        String title = borrowedBook.get(2);
        String status = borrowedBook.get(3);
        System.out.println(status);

        if (borrowedBook.isEmpty()) {
            throw new InvalidBookException("The program wasn't find book");
        }
        updateBookStatus.replaceBorrowedBookStatus(isbn);
        return new Book(isbn, author, title);
    }

    public List<String> borrowBook() {
        List<String> concludingBookList = new ArrayList<>();
        while (!bookAvailable) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the book isbn number,what do you want to borrow");
            System.out.println("If you want to come back to the main menu, type 'back'");
            String inputISBA = scanner.nextLine();
            if(!inputISBA.equals("back")){
            if (library.exists(new Book(inputISBA))) {
                if (checkAvailableBook(inputISBA)) {
                    System.out.println("Book is available");
                    concludingBookList.addAll(getBookInfoByISBN(inputISBA));
                    bookAvailable = true;
                }
            }
        }else {
                bookAvailable = true;
                break;
            }
        }
        return concludingBookList;
    }

    public List<String> getBookInfoByISBN(String transferIsbn) {
        List<String> bookDetails = new ArrayList<>();
        Book book = findBookByISBN(transferIsbn);
        if (book.getStatus().equals(Status.AVAILABLE)){
            bookDetails.add(book.getIsbn());
            bookDetails.add(book.getAuthor());
            bookDetails.add(book.getTitle());
            bookDetails.add(String.valueOf(book.getStatus()));
            System.out.println(bookDetails);
        } else {
            System.out.println("This book has been borrowed");
        }
        return bookDetails;
    }

    public Book findBookByISBN(String isbn) {
        List<Book> test1 = fileBookRepository.getAllBooks();
        Stream<Book> bookStream = test1.stream()
                .filter(book -> book.getIsbn().equals(isbn));
        List<Book> test2 = bookStream.collect(Collectors.toList());
        Book book = test2.get(0);
        return book;
    }

    private boolean checkAvailableBook(String ISBN) {
        if (getBookInfoByISBN(ISBN).isEmpty()) {
            return false;
        }
        return true;
    }

}
