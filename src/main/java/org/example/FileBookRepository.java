package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileBookRepository implements BookRepository {

    private String fileName;


    public FileBookRepository(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void saveBook(Book book) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(fileName,true));

            String saveBookInformation = book.convertToWrite();
            pw.println(saveBookInformation);

            pw.close();
        } catch (IOException e) {
            System.out.println("File isn't exist");
        }
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> listOfAllBooks = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNext()) {
                String readBooksInFile = scanner.nextLine();
                Book book = bookInfoConverter(readBooksInFile);
                if (book != null) {

                    listOfAllBooks.add(book);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File isn't exist");
        }

        return listOfAllBooks;
    }

    private Book bookInfoConverter(String bookInfo) {

        String[] informationAboutBook = bookInfo.split(",");

        if (informationAboutBook.length == 4) {
            String isbn = informationAboutBook[0].trim();
            String author = informationAboutBook[1].trim();
            String title = informationAboutBook[2].trim();
            String statusStr = informationAboutBook[3].trim().toUpperCase();

            Status status = checkStatus(statusStr);

            return new Book(isbn, author, title, status);
        }

        return null;

    }

    private Status checkStatus(String informationAboutStatus) {

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
