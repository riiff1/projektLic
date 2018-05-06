package com.dictionary.restController;

import com.dictionary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/loggedUser", method = RequestMethod.GET)
    public String loggedUserName() {
        return userService.getLoggedUser();
    }

    @RequestMapping(value = "/edit/userNameExist", method = RequestMethod.GET)
    public boolean userNameExist(@RequestParam("nick") String nick) {
        return userService.existUser(nick);
    }
}
