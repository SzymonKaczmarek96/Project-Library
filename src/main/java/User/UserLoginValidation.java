package User;

import com.sun.jdi.Value;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.Key;
import java.util.*;

public class UserLoginValidation<K,V> implements LoginValidation,UserInfoProvider<K,V>{

    private final String fileName = "src/main/resources/Users.txt";

    private Map<K,V> userRepository;

    public UserLoginValidation() {
        this.userRepository = addInformationAboutUsers();
    }

    private void checkLoginAndPasswordInFile(String loginInformation) {
        String username = "";
        String password = "";
        String[] login = loginInformation.split(",");
        if(login.length > 2) {
            username = login[1].trim();
            password = login[2].trim();
            userRepository.put((K)username,(V)password);
        }
        }
    @Override
    public Map<K,V> addInformationAboutUsers() {
      userRepository = new HashMap<>();

        try(Scanner sc = new Scanner(new File(fileName))){
            while (sc.hasNext()){
                String line = sc.nextLine();
                checkLoginAndPasswordInFile(line);
            }
        }catch(FileNotFoundException e){
            System.out.println("File, not exist");
        }
        return userRepository;
    }

    private List getKeys(Map map){
        return new ArrayList<>(map.keySet());
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
}



