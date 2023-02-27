package com.javarush.spring08.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserHandler {

    @GetMapping("/*")
    public ModelAndView homePage(ModelAndView view) {
        view.setViewName("index");
        return view;
    }

}
