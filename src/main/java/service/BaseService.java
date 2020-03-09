package service;

import document.IData;
import document.IEntity;
import java.util.List;

import document.InvalidData;
import document.ValidData;

public interface BaseService {

    <T extends IEntity> void checkEmailId(String database, String bucket, Class<T> cls, String ids);

    <E extends IEntity, T extends IData> void deleteEmailIds(String database, String bucket, Class<E> cls, List<T> entries);

    int getCount();

    boolean isInMongoDB();

    List<InvalidData> getInvalidDataList();

    List<ValidData> getValidDataList();

    void reset();
}
