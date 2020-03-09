package config;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import document.Broker;
import document.InternalUsers;
import document.Lender;
import document.User;
import service.BrokerService;
import service.InternalUsersService;
import service.LenderService;
import service.UserService;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableMongoRepositories
@EnableSwagger2
@Configuration
public class MongoDBConfig {

	UserService userService;
	BrokerService brokerService;
	LenderService lenderService;
	InternalUsersService internalUsersService;
	
	public MongoDBConfig() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public MongoDBConfig(UserService userDal, BrokerService brokerDal, LenderService lender,
			InternalUsersService internalUsers) {
		this.userService = userDal;
		this.brokerService = brokerDal;
		this.lenderService = lender;
		this.internalUsersService = internalUsers;
	}

	@PreDestroy
	void shutDown() {
		userService.deleteAll();
		brokerService.deleteAll();
		lenderService.deleteAll();
		internalUsersService.deleteAll();
	}

	@Bean
	CommandLineRunner commandLineRunner(UserService userDal, BrokerService brokerDal) {
		return data -> {
			saveEmailIds("1", "suresh@gmail.com");
			saveEmailIds("2", "john@gmail.com");
			saveEmailIds("3", "sita@gmail.com");
			saveEmailIds("4", "arun@gmail.com");
		};
	}

	void saveEmailIds(String id, String emailId) {
		userService.save(new User(id, emailId));
		brokerService.save(new Broker(id, emailId));
		lenderService.save(new Lender(id, emailId));
		internalUsersService.save(new InternalUsers(id, emailId));
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}

}
