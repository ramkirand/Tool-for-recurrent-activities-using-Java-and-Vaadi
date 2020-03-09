package repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import document.Lender;
import document.IData;
import service.LenderService;

@Repository
@Qualifier("lenderService")
public class LenderServiceImpl extends AbstractServiceImpl implements LenderService {

	@Autowired
	public LenderServiceImpl() {
		bucket = "lender";
		database = "test";
	}

	@Override
	public <T extends IData> void deleteLenders(List<T> entries) {
		super.deleteEmailIds(database, bucket, Lender.class, entries);
	}

	@Override
	public List<Lender> getAllLenders() {
		return super.findAll(Lender.class);
	}

	@Override
	public void save(Lender user) {
		super.save(Lender.class, user);
	}

	public void checkEmailId(String database, String bucket, String ids) {
		super.checkEmailId(database, bucket, Lender.class, ids);
	}

	@Override
	public void checkEmailId(String value) {
		checkEmailId(database, bucket, value);
	}

	@Override
	public void deleteAll() {
		super.deleteAll(Lender.class);
	}

	@Override
	public void deleteLender(String id) {
		super.delete(Lender.class, id);
	}

}
