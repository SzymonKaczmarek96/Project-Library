package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UserRegistrationSystem implements UserBase {

    @Override
    public boolean checkLogin(String username) {
        if(loginCondition(username)){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkPassword(String password, String username) {
        if(passwordConditions(password)){
            return true;
        }
        return false;
    }

    private boolean passwordConditions(String password) {
        if(password.length() > 18 || password.length() < 5){
            System.out.println("Password is too long or too short");
            return false;
        }
        return true;
    }

    private boolean loginCondition(String username) {
        if(username.length() < 3 || username.length() > 15) {
            System.out.println("Login is too long or too short");
            return false;
        }
        if(loginIsExists(username)){
            System.out.println("Login is already in use");
            return false;
        }
        return true;
    }

    private boolean loginIsExists(String username) {
        final String fileName = "src/main/resources/Users.txt";
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
            while(scanner.hasNext()){
                String line = scanner.nextLine();
                String checkLogin = readLoginFromFile(line);
                if(username.equals(checkLogin)){
                    return true;
                }
            }
        }catch (FileNotFoundException e){
            System.out.println("File not exist");
        }finally {
            if(scanner!= null){
                scanner.close();
            }
        }
        return false;
    }

    private String readLoginFromFile(String username) {
        String loginUser = "";
        String[] login = username.split(",");
        if(login.length > 2){
            loginUser = login[1].trim();
        }
        return loginUser;
    }
}
