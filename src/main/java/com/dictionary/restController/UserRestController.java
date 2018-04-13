package com.dictionary.restController;

import com.dictionary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping("/loggedUser")
    public String loggedUserName() {
        return userService.getLoggedUser();
    }
}