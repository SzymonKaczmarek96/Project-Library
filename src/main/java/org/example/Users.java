package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Users {
    private int id;

    private String login;

    private String pass;

    private String firstName;

    private String lastName;

    private UserStatus userStatus;

    private final String fileName = "src/main/resources/Users.txt";

    public Users(String login, String pass, String firstName, String lastName) {
        this.id = generateId();
        this.login = login.toLowerCase();
        this.pass = pass.toLowerCase();
        this.firstName = firstName.toUpperCase();
        this.lastName = lastName.toUpperCase();
        this.userStatus = UserStatus.STUDENT;
    }

    public int generateId(){
        return readFileToGenerateId().size() + 1;
    }

    private List<Integer> readFileToGenerateId() {
        Scanner scanner = null;
        List<Integer> idList = new ArrayList<>();
        try  {
            scanner = new Scanner(new File(fileName));
            while (scanner.hasNext()) {
                String idInfo = scanner.nextLine();
                int idIntoList = checkIdInFile(idInfo);
                idList.add(idIntoList);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File isn't exist");
        }finally {
            if(scanner != null) {
                scanner.close();
            }
        }
        return idList;
    }

    private int checkIdInFile(String idInformation) {
        int userIdIntoList = 0;
        String[] id = idInformation.split(",");
        if(id.length > 0) {
            String userId = id[0].trim();
            userIdIntoList = Integer.parseInt(userId);
        }
        return userIdIntoList;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userStatus=" + userStatus +
                '}';
    }
}





