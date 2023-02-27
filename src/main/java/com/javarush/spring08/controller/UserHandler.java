package com.javarush.spring08.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserHandler {

    @RequestMapping(method = RequestMethod.GET, value = "value")
    String homePage() {
        return "index";
    }

}
