package repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import document.IData;
import document.InternalUsers;
import service.InternalUsersService;
import repository.AbstractServiceImpl;

@Repository
@Qualifier("internalUsersService")
public class InternalUsersServiceImpl extends AbstractServiceImpl implements InternalUsersService {

	@Autowired
	public InternalUsersServiceImpl() {
		bucket = "internal";
		database = "test";
	}

	@Override
	public <T extends IData> void deleteInternalUsers(List<T> entries) {
		super.deleteEmailIds(database, bucket, InternalUsers.class, entries);
	}

	@Override
	public List<InternalUsers> getAllInternalUsers() {
		return super.findAll(InternalUsers.class);
	}

	@Override
	public void save(InternalUsers user) {
		super.save(InternalUsers.class, user);
	}

	public void checkEmailId(String database, String bucket, String ids) {
		super.checkEmailId(database, bucket, InternalUsers.class, ids);
	}

	@Override
	public void checkEmailId(String value) {
		checkEmailId(database, bucket, value);
	}

	@Override
	public void deleteAll() {
		super.deleteAll(InternalUsers.class);
	}

	@Override
	public void deleteInternalUser(String id) {
		super.delete(InternalUsers.class, id);
	}

}
