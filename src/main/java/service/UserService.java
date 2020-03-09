package service;

import document.IData;
import java.util.List;
import document.User;

public interface UserService extends BaseService {

    public List<User> getAllUsers();

    public <T extends IData> void deleteUsers(List<T> entries);
    
    public void deleteUser(String id);

    void save(User user);

    void deleteAll();

    public void checkEmailId(String value);
    
}
