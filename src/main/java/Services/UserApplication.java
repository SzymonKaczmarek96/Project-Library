package Services;

import Model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class UserApplication {

    private final UserLoginValidation userLoginValidation;

    private final UserRegistrationHandler userRegistrationHandler;

    private List<String> loginAndPasswordList;

    public UserApplication() {
        this.userLoginValidation = new UserLoginValidation();
        this.userRegistrationHandler = new UserRegistrationHandler();
    }

    public void registerUser(String registerUsername, String password) {
        Scanner scanner = new Scanner(System.in);
        introduceUserData(registerUsername, password);
        if (userRegistrationHandler.checkLogin(getUsername()) && userRegistrationHandler.checkPassword(getPassword())) {
            System.out.println("First name: ");
            String firstName = scanner.nextLine().toUpperCase();
            System.out.println("Last name: ");
            String lastName = scanner.nextLine().toUpperCase();
            User user = new User(getUsername(), getPassword(), firstName, lastName);
            userRegistrationHandler.savingUserToFile(user);
            loginAndPasswordList.clear();
        } else {
            System.out.println("Login or password don't make conditions");
        }
    }

    public boolean loginUser(String username, String password) {
        introduceUserData(username, password);
        if (userLoginValidation.checkLogin(getUsername())) {
            if (userLoginValidation.checkPassword(getUsername(), getPassword())) {
                System.out.println("Login and password correct");
                loginAndPasswordList.clear();
                return true;
            }
        }
        System.out.println("Error, login or password is incorrect");
        return false;
    }


    private void introduceUserData(String username, String password) {
        loginAndPasswordList = new ArrayList<>();
        loginAndPasswordList.add(username);
        loginAndPasswordList.add(password);
    }

    private String getUsername() {
        String userName = loginAndPasswordList.get(0);
        return userName;
    }

    private String getPassword() {
        String password = loginAndPasswordList.get(1);
        return password;
    }


}
