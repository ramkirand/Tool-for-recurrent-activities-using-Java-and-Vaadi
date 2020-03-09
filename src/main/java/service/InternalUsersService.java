package service;

import java.util.List;

import document.IData;
import document.InternalUsers;

public interface InternalUsersService extends BaseService {

    public List<InternalUsers> getAllInternalUsers();

    public <T extends IData> void deleteInternalUsers(List<T> entries);
    
    public void deleteInternalUser(String id);
    
    void save(InternalUsers broker);
    
    void deleteAll();

    public void checkEmailId(String value);
}
