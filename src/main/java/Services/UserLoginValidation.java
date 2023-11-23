package Services;

import Model.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class UserLoginValidation<K, V> implements LoginValidation, UserInfoProvider<K, V> {

    private final String FILE_NAME = "src/main/resources/Users.txt";

    private Map<K, V> userRepository;

    public UserLoginValidation() {
        this.userRepository = addInformationAboutUsers();
    }

    private void checkLoginAndPasswordInFile(String loginInformation) {
        String username = "";
        String password = "";
        String[] login = loginInformation.split(",");
        if (login.length > 2) {
            username = login[1].trim();
            password = login[2].trim();
            userRepository.put((K) username, (V) password);
        }
    }
    private List getKeys(Map map) {
        return new ArrayList<>(map.keySet());
    }

    public String searchUser(String username) {
        try {
            Path path = Paths.get(FILE_NAME);
            List<String> fileLines = Files.readAllLines(path);
            for (String line : fileLines) {
                if (line.contains(username)) {
                    return line;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public User informationAboutUser(String enterUsername){
        String loadInformation = searchUser(enterUsername);
        String[] userInformation = loadInformation.split(",");
        String userID = userInformation[0].trim();
        String firstName = userInformation[3].trim();
        String lastName = userInformation[4].trim();
        return new User(userID,firstName,lastName);
    }

    @Override
    public boolean checkUserStatus(String username) {
        String userStatusInformation = searchUser(username);
        String[] userInformation = userStatusInformation.split(",");
        String status = userInformation[5].trim();

        if(status.equals("ADMIN")){
            return true;
        }
        return false;
    }


    @Override
    public Map<K, V> addInformationAboutUsers() {
        userRepository = new HashMap<>();
        try (Scanner sc = new Scanner(new File(FILE_NAME))) {
            while (sc.hasNext()) {
                String line = sc.nextLine();
                checkLoginAndPasswordInFile(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File, not exist");
        }
        return userRepository;
    }

    @Override
    public boolean checkLogin(String username) {
        List userList = getKeys(userRepository);
        return userList.contains(username);
    }

    @Override
    public boolean checkPassword(String username, String password) {
        return userRepository.get(username).equals(password);
    }

}



