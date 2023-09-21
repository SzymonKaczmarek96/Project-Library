package org.example;

import FileException.InvalidInputException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {

    private boolean start;
    private final int MENU_SIZE = 6;


    public Application() {
        this.start = false;
    }

    public void mainApplication() {

        Scanner scanner = new Scanner(System.in);

        while (!start) {

            System.out.println("1");
            System.out.println("2");
            System.out.println("3");
            System.out.println("4");
            System.out.println("5");
            System.out.println("6.Exit");

            try {
                int userChoice = scanner.nextInt();

                if (userChoice == 6) {
                    System.out.println("Program will be a closed");
                    start = true;
                } else if (userChoice < 1 || userChoice > MENU_SIZE) {
                    throw new InvalidInputException("Invalid menu choice. Value can't be more than " + MENU_SIZE + " or less than 1.");
                } else {

                }} catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }
        }




