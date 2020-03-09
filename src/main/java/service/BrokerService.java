package service;

import java.util.List;

import document.Broker;
import document.IData;

public interface BrokerService extends BaseService {

    public List<Broker> getAllBrokers();

    public <T extends IData> void deleteBrokers(List<T> entries);
    
    public void deleteBroker(String id);
    
    void save(Broker broker);
    
    void deleteAll();

    public void checkEmailId(String value);
}
