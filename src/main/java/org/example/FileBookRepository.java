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
            PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(fileName,true));
            String saveBookInformation = book.convertBookToSave();
            pw.println(saveBookInformation);
        } catch (IOException e) {
            System.out.println("File isn't exist");
        }finally {
            if(pw != null) {
                pw.close();
            }
        }
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> listOfAllBooks = new ArrayList<>();
        try(Scanner scanner = new Scanner(new File(fileName))) {
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

    public List<Book> convertToBook(String bookInfo) {
        List<Book> books = new ArrayList<>();

        String[] informationAboutBook = bookInfo.split(",");

        if (informationAboutBook.length == 4) {
            String isbn = informationAboutBook[0].trim();
            String author = informationAboutBook[1].trim();
            String title = informationAboutBook[2].trim();
            String statusStr = informationAboutBook[3].trim().toUpperCase();

            Status status = checkStatus(statusStr);

            books.add(new Book(isbn,author,title,status));
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
