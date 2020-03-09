package repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import document.Broker;
import document.IData;
import service.BrokerService;


@Repository
public class BrokerServiceImpl extends AbstractServiceImpl implements BrokerService {

    @Autowired
    public BrokerServiceImpl() {
        bucket = "broker";
        database = "test";
    }

    @Override
    public <T extends IData> void deleteBrokers(List<T> entries) {
        super.deleteEmailIds(database, bucket, Broker.class, entries);
    }

    @Override
    public List<Broker> getAllBrokers() {
        return super.findAll(Broker.class);
    }

    @Override
    public void save(Broker user) {
        super.save(Broker.class, user);
    }

    public void checkEmailId(String database, String bucket, String ids) {
        super.checkEmailId(database, bucket, Broker.class, ids);
    }

    @Override
    public void checkEmailId(String value) {
        checkEmailId(database, bucket, value);
    }

    @Override
    public void deleteAll() {
        super.deleteAll(Broker.class);
    }

    @Override
    public void deleteBroker(String id) {
        super.delete(Broker.class, id);
    }
    
    
}
