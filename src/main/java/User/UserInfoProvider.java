package User;

import java.util.Map;

public interface UserInfoProvider<K,V>{
    Map<K,V> addInformationAboutUsers();
}
