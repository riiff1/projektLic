package com.dictionary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ScheduleController {

    @RequestMapping(value = "/schedule", method = RequestMethod.GET)
    public String schedule() {
        return "schedule";
    }
}
