package com.javarush.spring18.controller.html;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomePageController {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String getHomePage(){
        return "redirect:/users/";
    }

    //18 lesson
    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String getLoginPage(){
        return "/login";
    }
}
