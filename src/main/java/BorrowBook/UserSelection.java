package BorrowBook;

import User.UserApplication;
import User.Users;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserSelection {

    private final String FILE_NAME = "src/main/resources/Users.txt";

   private UserApplication userApplication;

   private Scanner scanner;

   private List<String> userBorrowedBook;

    public UserSelection() {
        this.userApplication = new UserApplication();
        this.userBorrowedBook = loginUserIntoLibrary();
    }
    public Users createUserForBorrowedBook() {

        if(userBorrowedBook.size() != 3) {
            System.out.println("Invalid data in userBorrowedBook");
            return new Users("error", "error", "error");
        }
        String id = userBorrowedBook.get(0);
        String firstName = userBorrowedBook.get(1);
        String lastName = userBorrowedBook.get(2);

        if(id == null || firstName.isBlank() || lastName.isBlank()){
            System.out.println("Username or password cannot be empty");
            return new Users("error", "error", "error");
        }
        return new Users(id,firstName,lastName);
           }

    private List<String> loginUserIntoLibrary() {
        List<String> transferUser = new ArrayList<>();

        scanner = new Scanner(System.in);

        System.out.println("Login: ");

        String username = scanner.nextLine();

        System.out.println("Password: ");

        String password = scanner.nextLine();

        if(userApplication.loginUser(username,password)){
            transferUser.addAll(getInfoAboutUser(username,password));

            return transferUser;
        }else {

        }

        return transferUser;
    }

    private List<String> getInfoAboutUser(String username,String password) {
        List<String> getIdAndUserName = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(FILE_NAME))){
            while (sc.hasNext()){
                String userInformation = sc.nextLine();
                getIdAndUserName.addAll(convertUserFile(userInformation,username,password));
            }
        }catch(FileNotFoundException e){
            System.out.println("File not exist");
        }
        return getIdAndUserName;
    }


    private List<String> convertUserFile(String userInfo,String username, String userPass){
        List<String> userList = new ArrayList<>();

        String[] userInfoColl = userInfo.split(",");
        if(userInfoColl.length == 6){
            String id = userInfoColl[0].trim();
            String login = userInfoColl[1].trim();
            String password = userInfoColl[2].trim();
            String firstName = userInfoColl[3].trim();
            String lastName = userInfoColl[4].trim();

            if(login.equals(username) && password.equals(userPass)) {
                userList.add(id);
                userList.add(firstName);
                userList.add(lastName);
            }

        }
        return userList;
    }
}
