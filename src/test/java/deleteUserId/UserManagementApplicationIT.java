//package deleteUserId;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import ui.UserManagementApplication;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = UserManagementApplication.class)
//@AutoConfigureMockMvc
//public class UserManagementApplicationIT {
//
//	@Autowired
//	MockMvc mockMvc;
//
//
//	@Test
//	public void contextLoads() throws Exception {
//		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/id//").accept(MediaType.APPLICATION_JSON))
//				.andReturn();
//	}
//
//}
