package org.example;

import FileException.InvalidInputException;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class MainApplication {

    private boolean start;
    private final int MENU_SIZE = 8;

    public MainApplication() {
        this.start = false;

    }

    public void startApp() {

        Scanner scanner = new Scanner(System.in);
        try{
        while (!start) {
            displayMenu();
            userChoice(scanner);
        }
        }finally {
            scanner.close();
        }
    }
        private void displayMenu() {
            System.out.println("1. Display all books"); // wyświetl wszystkie dostępne książki
            System.out.println("2. Search for the book"); // moze wyszukac książkę za pomoc ISBN, autora lub tytułu;
            System.out.println("3. Borrow the book"); // tylko i wyłącznie kiedy książka ma status Available oraz użytkownik nie ma wypożyczonej książki
            System.out.println("4. Return the book"); // książka zmieni status na dostępną oraz zniknie z pliku wypożyczonych
            System.out.println("5. View users with borrowed a book"); // Ta funkcja powinna być dostępna tylko dla admina z uprawnieniami
            System.out.println("6. Add book to library"); // Ta funkcja powinna być dostępna tylko dla admina z uprawnieniami
            System.out.println("7. Delete book with repository"); // Ta funkcja powinna być dostępna tylko dla admina z uprawnieniami
            System.out.println("8. Exit"); // Zamykanie programu.
        }

        private void userChoice( Scanner scanner) {
            Library library = new Library();
            String numberChoice = scanner.nextLine();
            try {
                if (numberChoice.equals("1")) {
                    library.displayBooks();
                }
                if (numberChoice.equals("8")) {
                    System.out.println("Program will be a closed");
                    start = true;
                }else {
                    throw new InvalidInputException ("Invalid menu choice. Value can't be more than " + MENU_SIZE + " or less than 1");
                }
            }  catch (InvalidInputException e) {
                System.out.println(e.getMessage());

            }
        }
        }















