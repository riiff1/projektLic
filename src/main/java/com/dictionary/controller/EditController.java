package com.dictionary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/edit")
public class EditController {

    @RequestMapping("/user")
    public String editUser() {
        return "editUser";
    }
}
