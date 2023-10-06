package User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Users {
    private int id;

    private String login;

    private String pass;

    private String firstName;

    private String lastName;

    private UserStatus userStatus;

    private final String fileName = "src/main/resources/Users.txt";

    public Users(String login, String pass, String firstName, String lastName) {
        this.id = generateId(readFileToGenerateId());
        this.login = login.toLowerCase();
        this.pass = pass.toLowerCase();
        this.firstName = firstName.toUpperCase();
        this.lastName = lastName.toUpperCase();
        this.userStatus = UserStatus.STUDENT;
    }

    public int generateId(List<Integer> numberId){

        Set<Integer> checkTheSmallestNumber = new HashSet<>(numberId);
        int n = numberId.size() + 1;
        for (int i = 1; i <=n;i++){
            if(!checkTheSmallestNumber.contains(i)){
                return i;
            }
        }
        return n + 1;
    }

    private List<Integer> readFileToGenerateId() {
        List<Integer> idList = new ArrayList<>();
        try(Scanner scanner = new Scanner(new File(fileName)))  {
            ;
            while (scanner.hasNext()) {
                String idInfo = scanner.nextLine();
                if(idInfo.isEmpty()){
                 continue;
                }else {
                    int idIntoList = checkIdInFile(idInfo);
                    idList.add(idIntoList);
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("File isn't exist");
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

    public String convertUserToSave(){
        return String.format("%s,%s,%s,%s,%s,%s",id,login,pass,firstName,lastName,userStatus);
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





