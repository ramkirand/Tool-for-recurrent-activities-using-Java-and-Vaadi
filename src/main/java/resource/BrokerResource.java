package resource;
/*
D Rama Kiron
*/
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import document.Broker;
import io.swagger.annotations.ApiOperation;
import service.BrokerService;

@RestController
@RequestMapping("/brokers")
public class BrokerResource {

	private final BrokerService brokerService;

	public BrokerResource(BrokerService brokerDal) {
		this.brokerService = brokerDal;
	}

	@GetMapping("/")
	public String index() {
		return "Welcome to the home page!";
	}

	@ApiOperation(value = "Get all brokers", response = List.class)
	@GetMapping("/all")
	public List<Broker> getAll() {
		return brokerService.getAllBrokers();
	}

	@ApiOperation(value = "Enter comma seperated User id's")
	@DeleteMapping("/id")
	public void deleteBrokers(String ids) {
		brokerService.deleteBroker(ids);
	}

}
