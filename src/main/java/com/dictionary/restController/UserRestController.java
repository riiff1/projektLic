package com.dictionary.restController;

import com.dictionary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/loggedUser", method = RequestMethod.GET)
    public String loggedUserName() {
        System.out.println(userService.getLoggedUser());
        return userService.getLoggedUser();
    }
}
