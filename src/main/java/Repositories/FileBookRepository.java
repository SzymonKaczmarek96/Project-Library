package Repositories;

import Model.Book;
import Model.Status;
import Services.UpdateBookStatus;
import Services.UserApplication;
import Services.UserLoginValidation;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FileBookRepository implements BookRepository {

    private final String FILE_NAME = "src\\main\\resources\\BookRepository.txt";

    private UserApplication userApplication;

    private UserLoginValidation userLoginValidation;

    public FileBookRepository() {
        this.userApplication = new UserApplication();
        this.userLoginValidation = new UserLoginValidation();
    }

    @Override
    public void saveBook(Book book) {

        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            String saveBookInformation = book.convertBookToSave();
            pw.println(saveBookInformation);
        } catch (IOException e) {
            System.out.println("File isn't exist");
        }
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> listOfAllBooks = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(FILE_NAME))) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                List<Book> books = convertToBook(line);
                listOfAllBooks.addAll(books);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File isn't exist");
        }
        return listOfAllBooks;
    }

    @Override
    public Book bookProperties() {
        Status status = Status.AVAILABLE;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter isbn number: ");
        String isbn = scanner.nextLine();
        System.out.println("Enter author: ");
        String author = scanner.nextLine().toUpperCase();
        System.out.println("Enter title: ");
        String bookTitle = scanner.nextLine().toUpperCase();

        return new Book(isbn, author, bookTitle, status);
    }

    @Override
    public void deleteBookWithRepository(String ISBN) {
        try {
            Files.write(
                    Paths.get(FILE_NAME),
                    Files.lines(Paths.get(FILE_NAME))
                            .filter(line -> !line.contains(ISBN))
                            .collect(Collectors.toList()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void searchBook(String dataForSearch) {
        try {
            Path path = Paths.get(FILE_NAME);
            List<String> fileLines = Files.readAllLines(path);
            for (String line : fileLines) {
                if (line.contains(dataForSearch)) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Book> convertToBook(String bookInfo) {
        List<Book> books = new ArrayList<>();
        String[] informationAboutBook = bookInfo.split(",");
        if (informationAboutBook.length == 4) {
            String isbn = informationAboutBook[0].trim();
            String author = informationAboutBook[1].trim();
            String title = informationAboutBook[2].trim();
            String statusStr = informationAboutBook[3].trim().toUpperCase();

            Status status = checkStatus(statusStr);

            books.add(new Book(isbn, author, title, status));
        }
        return books;
    }

    public Status checkStatus(String informationAboutStatus) {
        switch (informationAboutStatus) {
            case "AVAILABLE":
                return Status.AVAILABLE;
            case "NOT_AVAILABLE":
                return Status.NOT_AVAILABLE;
            case "BORROWED":
                return Status.BORROWED;
            default:
                System.out.println("Status is not available");
                return Status.NOT_AVAILABLE;
        }
    }


}
