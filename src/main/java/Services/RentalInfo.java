package Services;

//ID-book|title|author|rental-date|rental-time|borrower-id|borrower-name|rental-in-days

import Model.Book;
import Model.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// login into system is the first step
//
public class RentalInfo {

    private final Book book;
    private final User user;
    private final LocalDate rentalDate;
    private final long rentalTime;

    public RentalInfo(User user, Book book) {
        this.user = user;
        this.book = book;
        this.rentalDate = getRentalDate();
        this.rentalTime = 0;
    }

    public RentalInfo(User user, Book book, LocalDate rentalDate) {
        this.user = user;
        this.book = book;
        this.rentalDate = rentalDate;
        this.rentalTime = calculateRentalInDays(rentalDate);
    }

    private LocalDate getRentalDate() {

        LocalDate date = LocalDate.now();

        return date;

    }

    private long calculateRentalInDays(LocalDate dateInFile) {
        LocalDate date2 = LocalDate.now();
        return ChronoUnit.DAYS.between(dateInFile, date2);

    }

    public String convertRentalInfoToSave() {

        return String.format("%s,%s,%s", book, user, rentalDate);
    }

    public User getUsers() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    @Override
    public String toString() {
        return
                "book= " + book +
                        ", users= " + user +
                        ", rentalDate= " + rentalDate +
                        ", rentalTime= " + (rentalTime == 0 ? " today" : rentalTime + " days")
                ;
    }
}
