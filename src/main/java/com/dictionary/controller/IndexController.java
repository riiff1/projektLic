package com.dictionary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/register")
    public String register() {
        return "log/register";
    }

    @RequestMapping("/login")
    public String login() {
         return "log/login";
    }

    @RequestMapping("/logoutt")
    public String logout(HttpSession session) {
        session.invalidate();
        return "log/logoutt";
    }
}
