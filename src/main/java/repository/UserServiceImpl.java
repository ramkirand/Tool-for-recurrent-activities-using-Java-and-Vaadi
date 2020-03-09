package repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import document.IData;
import document.User;
import service.UserService;

@Repository
public class UserServiceImpl extends AbstractServiceImpl implements UserService {

    @Autowired
    public UserServiceImpl() {
        bucket = "user";
        database = "test";
    }

    @Override
    public <T extends IData> void deleteUsers(List<T> entries) {
        super.deleteEmailIds(database, bucket, User.class, entries);
    }

    @Override
    public List<User> getAllUsers() {
        return super.findAll(User.class);
    }

    @Override
    public void save(User user) {
        super.save(User.class, user);
    }

    public void checkEmailId(String database, String bucket, String ids) {
        super.checkEmailId(database, bucket, User.class, ids);
    }

    @Override
    public void checkEmailId(String value) {
        checkEmailId(database, bucket, value);
    }

    @Override
    public void deleteAll() {
        super.deleteAll(User.class);
    }

    @Override
    public void deleteUser(String id) {
        super.delete(User.class, id);
    }
}
