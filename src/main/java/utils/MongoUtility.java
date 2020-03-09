package utils;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * D Rama Kiron
 */
public enum MongoUtility {

    INSTANCE;

    private final Map<String, MongoDatabase> databaseMap = new HashMap<>();
    private final CodecRegistry pojoCodecRegistry;

    private final MongoClient mongo;

    private MongoUtility() {
        pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        mongo = new MongoClient();
    }

    public void closeMongoDB() {
        Optional.of(mongo).ifPresent(mongoInstance -> mongoInstance.close());
    }

    public MongoDatabase getDatabase(String database) {
        MongoDatabase mongoDB = databaseMap.get(database);
        if (mongoDB == null) {
            mongoDB = mongo.getDatabase(database);
            mongoDB = mongoDB.withCodecRegistry(pojoCodecRegistry);
            databaseMap.put(database, mongoDB);
        }
        return mongoDB;
    }

    public <T> MongoCollection<T> getBucket(String database, String bucket, Class<T> cls) {
        MongoDatabase mongoDB = getDatabase(database);
        mongoDB.withCodecRegistry(pojoCodecRegistry);
        return mongoDB.getCollection(bucket, cls);
    }
}
