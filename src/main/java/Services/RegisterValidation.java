package Services;

import Model.User;

public interface RegisterValidation {
    boolean checkLogin(String username);

    boolean checkPassword(String password);

    void savingUserToFile(User user);


}
