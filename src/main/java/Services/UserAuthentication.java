package Services;

import Model.User;

import java.util.Scanner;

public class UserAuthentication {

    private final String FILE_NAME = "src/main/resources/Users.txt";

    private final UserApplication userApplication;

    private Scanner scanner;

    private User loggedUser;

    private UserLoginValidation userLoginValidation;


    public UserAuthentication() {
        this.userLoginValidation = new UserLoginValidation();
        this.userApplication = new UserApplication();
        this.loggedUser = provideUserAuthentication();
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public User provideUserAuthentication() {
        scanner = new Scanner(System.in);
        System.out.println("Login: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        if (userApplication.loginUser(username,password)) {
            loggedUser = userLoginValidation.informationAboutUser(username);;
        } else {
            System.out.println(false);
        }
        return loggedUser;
    }
}
