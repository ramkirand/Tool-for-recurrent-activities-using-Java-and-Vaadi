package service;

import java.util.List;

import document.IData;
import document.Lender;

public interface LenderService extends BaseService {

    public List<Lender> getAllLenders();

    public <T extends IData> void deleteLenders(List<T> entries);
    
    public void deleteLender(String id);
    
    void save(Lender broker);
    
    void deleteAll();

    public void checkEmailId(String value);
}
