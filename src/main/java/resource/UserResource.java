package resource;
/*
D Rama Kiron
*/
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import document.User;
import io.swagger.annotations.ApiOperation;
import service.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userDal) {
        this.userService = userDal;
    }

    @ApiOperation(value = "Get all brokers", response = List.class)
    @GetMapping("/all")
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @ApiOperation(value = "Enter comma seperated User id's")
    @DeleteMapping("/id")
    public void deleteBrokers(String ids) {
        userService.deleteUser(ids);
    }

}
