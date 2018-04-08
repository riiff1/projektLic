package com.dictionary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "log/register";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
         return "log/login";
    }

    @RequestMapping(value = "/logoutt", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "log/logoutt";
    }
}
