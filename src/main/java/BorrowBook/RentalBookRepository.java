package BorrowBook;

import java.util.List;

public interface RentalBookRepository {

    void saveBorrowedBooks();

    void viewsBorrowedBooks();

    void deleteReturnedBooks();

    void possibilityOfRental();



}
