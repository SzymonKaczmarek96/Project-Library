package User;

public interface RegisterValidation {
    boolean checkLogin(String username);
    boolean checkPassword(String password);

    void savingUserToFile(Users users);


}
