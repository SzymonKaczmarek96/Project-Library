package org.example;

import java.util.Scanner;


// ToDo : create list to add new users to file.txt.
public class UserApplication {

    private UserLoginSystem userBase;

    private UserRegistrationSystem userBaseToRegistration;

    public UserApplication() {
        this.userBase = new UserLoginSystem();
        this.userBaseToRegistration = new UserRegistrationSystem();
    }

    public Users userRegistration() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Login: ");
        String username = scanner.nextLine().toLowerCase();

        System.out.println("Password: ");
        String password = scanner.nextLine().toLowerCase();

        if(userBaseToRegistration.checkLogin(username) && userBaseToRegistration.checkPassword(password,username)){

            System.out.println("First name: ");
            String firstName = scanner.nextLine().toUpperCase();

            System.out.println("Last name: ");
            String lastName = scanner.nextLine().toUpperCase();
        }else {
            System.out.println("Login or password don't make conditions");
        }


        return null;
    }

    public boolean loggingIntoTheSystem() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Login: ");
        String username = scanner.nextLine().toLowerCase();

        System.out.println("Password: ");
        String password = scanner.nextLine().toLowerCase();

        if(userBase.checkLogin(username)){
            if(userBase.checkPassword(username,password)) {
                System.out.println("Login and password correct");
                return true;
            }
        }
        System.out.println("Error, login or password is incorrect");
        scanner.close();
        return false;
    }



}
