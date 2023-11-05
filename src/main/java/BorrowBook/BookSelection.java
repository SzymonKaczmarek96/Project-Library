package BorrowBook;

import org.example.Book;
import org.example.Library;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookSelection {

    private final String FILE_NAME = "src/main/resources/BookRepository.txt";

    private final String FILE_RENTAL_NAME = "src/main/resources/Rental.txt";

    private Library library;

    private List<String> BorrowedBook;

    private UpgradeBookStatus upgradeBookStatus;

    public BookSelection() {
        this.library = new Library();
        this.BorrowedBook = borrowBook();
        this.upgradeBookStatus = new UpgradeBookStatus();

    }

    public Book bookToBorrow() {
        if(BorrowedBook.size() != 4){
            System.out.println("Invalid data");
            return new Book("error");
        }
        String isbn = BorrowedBook.get(0);
        String author = BorrowedBook.get(1);
        String title = BorrowedBook.get(2);
        String status = BorrowedBook.get(3);
        System.out.println(status);

        if(isbn == null || author.isBlank() || title.isBlank() || status.equals("BORROWED")){
            return new Book("error");
        }
        upgradeBookStatus.replaceBookStatus(isbn);
        return new Book(isbn, author, title);
    }

    public List<String> borrowBook() {
        List<String> concludingBookList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the book isbn number,what do you want to borrow");
        String inputISBA = scanner.nextLine();

        if(library.exists(new Book(inputISBA))){
            System.out.println("Book in repository");
            concludingBookList.addAll(readBookRepository(inputISBA));
        }
        return concludingBookList;
    }

    public List<String> readBookRepository(String informationToSearchBook) {
        List<String> bookInformation = new ArrayList<>();

        try(Scanner sc =new Scanner(new File(FILE_NAME))){
            while (sc.hasNext()){
                String line = sc.nextLine();
                if(checkingTheBook(informationToSearchBook)) {
                    bookInformation.addAll(convertBookFile(line, informationToSearchBook));
                }
            }

        }catch (FileNotFoundException e){
            System.out.println("File not exit");
        }
        return bookInformation;
    }

    public List<String> convertBookFile(String bookInfo,String introduceISBN) {
        List<String> bookList = new ArrayList<>();

        String[] bookInfoColl = bookInfo.split(",");

        if(bookInfo.length() != 4) {

            String bookIsbn = bookInfoColl[0].trim();
            String author = bookInfoColl[1].trim();
            String title = bookInfoColl[2].trim();
            String status = bookInfoColl[3].trim();

            if(introduceISBN.equals(bookIsbn)){
                bookList.add(bookIsbn);
                bookList.add(author);
                bookList.add(title);
                bookList.add(status);
            }

        }
        return bookList;
    }
    public boolean checkingTheBook(String outsideIsbn) {
        List<String> checkIsbn = new ArrayList<>();
        checkIsbn.addAll(readRentalFile());
        for(String isbn: checkIsbn){
            if (outsideIsbn.equals(isbn)){
                return false;
            }
        }
        return true;
    }
    public List<String> readRentalFile() {
        List<String> rentalList = new ArrayList<>();
        try(Scanner scanner = new Scanner(new File(FILE_RENTAL_NAME))) {
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                rentalList.addAll(convertRentalFile(line));
            }
        }catch (FileNotFoundException e){
            System.out.println("File, not exist");
        }

        return rentalList;

    }
    public List<String> convertRentalFile(String rentalInfo) {
        List<String> isbnList = new ArrayList<>();

        String[] arr = rentalInfo.split(",");
        if(rentalInfo.length() != 4) {
            String rentalISBN = arr[0].trim();
            isbnList.add(rentalISBN);
        }
        return isbnList;
    }
}
