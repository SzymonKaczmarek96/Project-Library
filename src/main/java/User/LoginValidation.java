package User;

public interface LoginValidation {
    boolean checkLogin(String username);
    boolean checkPassword(String username, String password);
}
