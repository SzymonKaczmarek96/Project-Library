package Services;

import API.MainApplication;
import Model.User;

import java.util.Scanner;

public class UserAuthentication {

    private final UserApplication userApplication;

    private Scanner scanner;

    private User loggedUser;

    private UserLoginValidation userLoginValidation;

    private boolean check;

    public UserAuthentication() {
        this.userLoginValidation = new UserLoginValidation();
        this.userApplication = new UserApplication();
        this.loggedUser = provideUserAuthentication();
        this.check = false;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public User provideUserAuthentication() {
        while (!check) {
            scanner = new Scanner(System.in);
            System.out.println("Login: ");
            String username = scanner.nextLine();
            System.out.println("Password: ");
            String password = scanner.nextLine();
            System.out.println("If you want to come back to the main menu, type 'back'");
            if(username.equals("back") || password.equals("back")){
                check =true;
                break;
            }
            else if (checkUser(username, password)) {
                if (userApplication.loginUser(username, password)) {
                    if(username.equals(null) || password.equals(null)){
                        System.out.println("Error, user isn't registration");
                        check = true;
                        break;
                    }else {
                        loggedUser = userLoginValidation.informationAboutUser(username);
                        check = true;
                        break;
                    }
                }
            }else {
                System.out.println("Login or password are incorrect");
                check = true;
                break;
            }
        }
        return loggedUser;
    }

    private boolean checkUser(String username, String password) {
        if(username.isEmpty() || password.isEmpty()){
            return false;
        } else if(username.equals("back") || password.equals("back")){
            return false;
        }
        return true;
    }
}
