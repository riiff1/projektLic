package com.dictionary.restController;

import com.dictionary.dto.UserDto;
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

    @RequestMapping(value = "/edit/updatePass", method = RequestMethod.POST)
    public boolean updatePass(@RequestParam("newPass") String newPass, @RequestParam("passw") String passw) {
        return userService.updatePassForCurrentUser(newPass, passw);
    }

    @RequestMapping(value = "/edit/updateEmail", method = RequestMethod.POST)
    public boolean updateEmail(@RequestParam("newEmail") String newEmail, @RequestParam("passw") String passw) {
        return userService.updateEmailForCurrentUser(newEmail, passw);
    }

    @RequestMapping(value = "/edit/getNickAndEmail", method = RequestMethod.GET)
    public UserDto updateEmail() {
        return userService.getNickAndEmail();
    }
}
