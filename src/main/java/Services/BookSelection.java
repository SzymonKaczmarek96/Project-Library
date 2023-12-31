package Services;

import Model.Book;
import Model.Library;
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

    public BookSelection() {
        this.library = new Library();
        this.fileBookRepository = new FileBookRepository();
        this.borrowedBook = borrowBook();
        this.updateBookStatus = new UpdateBookStatus();
        this.fileRentalBookRepository = new FileRentalBookRepository();

    }

    public Book attemptBookBorrowing() {
        if (borrowedBook.size() != 4) {
            System.out.println("Invalid data");
            return new Book("error");
        }
        String isbn = borrowedBook.get(0);
        String author = borrowedBook.get(1);
        String title = borrowedBook.get(2);
        String status = borrowedBook.get(3);
        System.out.println(status);

        if (isbn == null || author.isBlank() || title.isBlank() || status.equals("BORROWED")) {
            return new Book("error");
        }
        updateBookStatus.replaceBookStatus(isbn);
        return new Book(isbn, author, title);
    }

    public List<String> borrowBook() {
        List<String> concludingBookList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the book isbn number,what do you want to borrow");
        String inputISBA = scanner.nextLine();

        if (library.exists(new Book(inputISBA))) {
            System.out.println("Book in repository");
            concludingBookList.addAll(getBookInfoByISBN(inputISBA));
        }
        return concludingBookList;
    }

    public List<String> getBookInfoByISBN(String transferIsbn) {
        List<String> test4 = new ArrayList<>();
        Book book = findBookByISBN(transferIsbn);
        test4.add(book.getIsbn());
        test4.add(book.getAuthor());
        test4.add(book.getTitle());
        test4.add(String.valueOf(book.getStatus()));
        System.out.println(test4);
        return test4;
    }

    public Book findBookByISBN(String isbn) {
        List<Book> test1 = fileBookRepository.getAllBooks();
        Stream<Book> bookStream = test1.stream()
                .filter(book -> book.getIsbn().equals(isbn));
        List<Book> test2 = bookStream.collect(Collectors.toList());
        Book book = test2.get(0);
        return book;
    }

}
