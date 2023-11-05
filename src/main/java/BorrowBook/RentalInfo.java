package BorrowBook;

//ID-book|title|author|rental-date|rental-time|borrower-id|borrower-name|rental-in-days

import User.Users;
import org.example.Book;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// login into system is the first step
//
public class RentalInfo {

    private Book book;
    private Users users;
    private LocalDate rentalDate;
    private long rentalTime;

    public RentalInfo(Users users,Book book) {
        this.users = users;
        this.book = book;
        this.rentalDate = getRentalDate();
        this.rentalTime = 0;
    }

    public RentalInfo(Users users,Book book, LocalDate rentalDate) {
        this.users = users;
        this.book = book;
        this.rentalDate = rentalDate;
        this.rentalTime = calculateRentalInDays(rentalDate);
    }

    private LocalDate getRentalDate() {

        LocalDate date = LocalDate.now();

        return date;

    }

    private long calculateRentalInDays(LocalDate dateInFile){
        LocalDate date2 = LocalDate.now();
        long daysDifference = ChronoUnit.DAYS.between(dateInFile,date2);
        return daysDifference;

    }

    public String convertRentalInfoToSave(){

        return String.format("%s,%s,%s",book,users,rentalDate);
    }

    public Users getUsers() {
        return users;
    }

    public Book getBook() {
        return book;
    }

    @Override
    public String toString() {
        return
                "book= " + book +
                ", users= " + users +
                ", rentalDate= " + rentalDate +
                ", rentalTime= " + rentalTime + " days"
                ;
    }
}
