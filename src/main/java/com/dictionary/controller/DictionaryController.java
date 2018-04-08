package com.dictionary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DictionaryController {

    @RequestMapping("/dictionary")
    public String index() {
        return "dictionary";
    }
}
