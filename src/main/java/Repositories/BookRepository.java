package Repositories;

import Model.Book;

import java.util.List;

public interface BookRepository {

    void saveBook(Book book);

    List<Book> getAllBooks();

    Book bookProperties();

    void deleteBookWithRepository(String ISBN);

    void searchBook(String dataForSearch);
}
