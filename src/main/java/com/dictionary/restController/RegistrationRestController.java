package com.dictionary.restController;

import com.dictionary.dto.UserDto;
import com.dictionary.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationRestController {

    @Autowired
    RegistrationService registrationService;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public void registerUser(@RequestParam("userName") String userName,
                             @RequestParam("password") String password,
                             @RequestParam("email") String email) {

        String crypted = new BCryptPasswordEncoder().encode(password);
        System.out.println(userName + " " + password + " " + email);
        UserDto newUser = new UserDto();
        newUser.setUserName(userName);
        newUser.setPassword(crypted);
        newUser.setEmail(email);
        newUser.setEnabled(true);
        registrationService.registerUser(newUser);

    }
}
