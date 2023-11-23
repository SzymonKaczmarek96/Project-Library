package API;

import Excaptions.InvalidInputException;
import Model.Library;

import java.util.Scanner;

public class MainApplication {

    private boolean start;
    private final int MENU_SIZE = 9;

    private final Library library;

    public MainApplication() {
        this.start = false;
        this.library = new Library();
    }

    public void startApp() {
        Scanner scanner = new Scanner(System.in);
        while (!start) {
            displayMenu();
            String numberChoice = scanner.nextLine();
            userChoice(numberChoice);
        }
        scanner.close();
    }

    private void displayMenu() {
        System.out.println("1. Display all books");
        System.out.println("2. Search for the book");
        System.out.println("3. Borrow the book");
        System.out.println("4. Return the book");
        System.out.println("5. View users with borrowed a book");
        System.out.println("6. Add book to library");
        System.out.println("7. Delete book with repository");
        System.out.println("8. Register user");
        System.out.println("9. Exit");
    }

    private void userChoice(String choice) {

        // switch
        try {
            switch (choice) {
                case "1" -> library.displayBooks();
                case "2" -> library.searchBooks();
                case "3" -> library.borrow();
                case "4" -> library.returnBook();
                case "5" -> library.displayRentalBook();
                case "6" -> library.addBookToRepository();
                case "7" -> library.removeBookWithLibrary();
                case "8" -> library.regiUser();
                case "9" -> {
                    System.out.println("Program will be a closed");
                    start = true;
                }
                default -> {
                    throw new InvalidInputException("Invalid menu choice. Value can't be more than " + MENU_SIZE + " or less than 1");
                }
            }
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());

        }
    }
}















