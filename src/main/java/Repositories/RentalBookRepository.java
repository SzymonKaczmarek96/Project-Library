package Repositories;

public interface RentalBookRepository {

    void saveBorrowedBooks();

    void viewsBorrowedBooks();

    void removeBookFromRentalRepository(String ISBN);

}
