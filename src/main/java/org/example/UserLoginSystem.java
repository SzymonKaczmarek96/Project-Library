package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Map.Entry;

public class UserLoginSystem implements UserBase {

    private final String fileName = "src/main/resources/Users.txt";

    private Scanner scanner;

    private Map<String,String> userRepository;

    public UserLoginSystem() {
        this.userRepository = addInformationAboutUsers();
    }

    @Override
    public boolean checkLogin(String username){
        List userList = getKeys(userRepository);
        if(userList.contains(username)) {
            ;
            return true;
        }
        return false;
    }

    @Override
    public boolean checkPassword(String username, String password) {
            if (userRepository.get(username).equals(password)) {
                return true;
            }
            return false;
    }

    private void checkLoginAndPasswordInFile(String loginInformation) {
        String username = "";
        String password = "";
        String[] login = loginInformation.split(",");
        if(login.length > 2) {
            username = login[1].trim();
            password = login[2].trim();
            userRepository.put(username,password);
        }
        }

    private Map addInformationAboutUsers() {
        userRepository = new HashMap<>();
        scanner = null;
        try{
            scanner = new Scanner(new File(fileName));
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                checkLoginAndPasswordInFile(line);
            }
        }catch(FileNotFoundException e){
            System.out.println("File, not exist");
        }finally {
            if(scanner != null) {
                scanner.close();
            }
        }
        return userRepository;
    }

    private List getKeys(Map map){
        return new ArrayList<>(map.keySet());
    }
}



