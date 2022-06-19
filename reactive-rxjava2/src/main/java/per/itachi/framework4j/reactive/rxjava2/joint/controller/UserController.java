package per.itachi.framework4j.reactive.rxjava2.joint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.itachi.framework4j.reactive.rxjava2.app.service.UserService;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/show")
    public void showUsers() {
        userService.showUsers();
    }
}
