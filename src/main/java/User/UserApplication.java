package User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


// ToDo : create list to add new users to file.txt.
public class UserApplication {

    private UserLoginValidation userLoginValidation;

    private UserRegistrationHandler userRegistrationHandler;

    private List<String> loginAndPasswordList;

    public UserApplication() {
        this.userLoginValidation = new UserLoginValidation();
        this.userRegistrationHandler = new UserRegistrationHandler();
    }

    public void registerUser() {
        Scanner scanner = new Scanner(System.in);
        introduceUserData(scanner);
        if(userRegistrationHandler.checkLogin(getUsername()) && userRegistrationHandler.checkPassword(getPassword())){
            System.out.println("First name: ");
            String firstName = scanner.nextLine().toUpperCase();

            System.out.println("Last name: ");
            String lastName = scanner.nextLine().toUpperCase();
            Users users = new Users(getUsername(),getPassword(),firstName,lastName);
            userRegistrationHandler.savingUserToFile(users);
            loginAndPasswordList.clear();
        }else {
            System.out.println("Login or password don't make conditions");
        }
        scanner.close();
    }

    public boolean loginUser() {
        Scanner scanner = new Scanner(System.in);
        introduceUserData(scanner);
        if(userLoginValidation.checkLogin(getUsername())){
            if(userLoginValidation.checkPassword(getUsername(),getPassword())){
                System.out.println("Login and password correct");
                loginAndPasswordList.clear();
                return true;
            }
        }
        System.out.println("Error, login or password is incorrect");
        scanner.close();
        return false;
    }


    private void introduceUserData(Scanner scanner){
        loginAndPasswordList = new ArrayList<>();

        System.out.println("Login: ");
        String username = scanner.nextLine().toLowerCase();

        System.out.println("Password: ");
        String password = scanner.nextLine().toLowerCase();

        loginAndPasswordList.add(username);
        loginAndPasswordList.add(password);
    }

    private String getUsername(){
        String userName = loginAndPasswordList.get(0);
        return userName;
    }

    private String getPassword(){
        String password = loginAndPasswordList.get(1);
        return password;
    }



}
