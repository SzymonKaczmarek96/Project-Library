package User;

import java.io.*;
import java.util.Scanner;

public class UserRegistrationHandler implements RegisterValidation {

    private final String fileName = "src/main/resources/Users.txt";

    private boolean passwordConditions(String password) {
        if (password.length() > 18 || password.length() < 5) {
            System.out.println("Password is too long or too short");
            return false;
        }
        return true;
    }

    private boolean loginCondition(String username) {
        if (username.length() < 3 || username.length() > 15) {
            System.out.println("Login is too long or too short");
            return false;
        }
        if (exists(username)) {
            System.out.println("Login is already in use");
            return false;
        }
        return true;
    }

    private boolean exists(String username) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String checkLogin = readLoginFromFile(line);
                if (username.equals(checkLogin)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not exist");
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

    @Override
    public boolean checkLogin(String username) {
        if(loginCondition(username)){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkPassword(String password) {
        if(passwordConditions(password)){
            return true;
        }
        return false;
    }

    @Override
    public void savingUserToFile(Users users) {

        try(PrintWriter pw =  new PrintWriter(new FileWriter(fileName,true))){;
            String saveUserInformation = users.convertUserToSave();
            pw.println(saveUserInformation);
            System.out.println("Data has been saved");

        }catch (IOException e) {
            System.out.println("File not exist");
        }
    }
}
