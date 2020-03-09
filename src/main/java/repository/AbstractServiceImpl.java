package repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import document.IData;
import document.IEntity;
import document.InvalidData;
import document.ValidData;
import lombok.Getter;
import lombok.Setter;
import service.BaseService;
import utils.MongoUtility;
import validation.EmailCheck;

@Getter
@Setter
public abstract class AbstractServiceImpl implements BaseService {

	protected String bucket;
	protected String database;
	protected List<InvalidData> invalidDataList = Collections.synchronizedList(new ArrayList<>());
	protected List<ValidData> validDataList = Collections.synchronizedList(new ArrayList<>());
	protected AtomicInteger emailIdCount = new AtomicInteger(0);
	protected AtomicInteger deletedEmailIdCount = new AtomicInteger(0);
	protected MongoUtility utility = MongoUtility.INSTANCE;

	@Override
	public <E extends IEntity, T extends IData> void deleteEmailIds(String database, String bucket, Class<E> cls,
			List<T> entries) {
		MongoCollection<E> collection = utility.getBucket(database, bucket, cls);
		entries.stream().forEachOrdered(data -> {
			Optional.of(collection.findOneAndDelete(Filters.eq("emailIds", data.getEmailId()))).ifPresent(doc -> {
				deletedEmailIdCount.getAndIncrement();
			});
		});
	}

	@Override
	public boolean isInMongoDB() {
		return emailIdCount.get() > 0;
	}

	@Override
	public int getCount() {
		return deletedEmailIdCount.get();
	}

	@Override
	public <T extends IEntity> void checkEmailId(String database, String bucket, Class<T> cls, String ids) {
		MongoCollection<T> collection = utility.getBucket(database, bucket, cls);
		for (String id : ids.split(",|\n|\r")) {
			if (EmailCheck.validate(id.trim())) {
				FindIterable<T> itrbroker = collection.find(Filters.eq("emailIds", id), cls);

				if (itrbroker.iterator().hasNext()) {
					ValidData validData = new ValidData(id,
							"Present in  bucket[" + bucket + "] Mongo DB [" + database + "]");
					validDataList.add(validData);
					emailIdCount.getAndIncrement();
				} else {
					InvalidData invalidData = new InvalidData(id,
							"Not in  bucket[" + bucket + "] Mongo DB [" + database + "]");
					invalidDataList.add(invalidData);
				}
			} else {
				InvalidData invalidData = new InvalidData(id, "Invalid emailId");
				invalidDataList.add(invalidData);
			}
		}
	}

	@Override
	public void reset() {
		invalidDataList.clear();
		validDataList.clear();
		deletedEmailIdCount.set(0);
		emailIdCount.set(0);

	}

	protected <T> void save(Class<T> cls, T data) {
		utility.getBucket(database, bucket, cls).insertOne(data);
	}

	public <T> void delete(Class<T> cls, String id) {
		utility.getBucket(database, bucket, cls).findOneAndDelete(Filters.eq("emailids", id));
	}

	public <T> void deleteAll(Class<T> cls) {
		utility.getBucket(database, bucket, cls).drop();
	}

	protected <T> List<T> findAll(Class<T> cls) {
		FindIterable<T> itr = utility.getBucket(database, bucket, cls).find(cls);
		List<T> list = new ArrayList<>();
		Consumer<T> consume = c -> {
			list.add(c);
		};
		itr.forEach(consume);
		return list;
	}

}
