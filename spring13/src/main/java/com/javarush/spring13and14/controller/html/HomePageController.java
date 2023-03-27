package com.javarush.spring13and14.controller.html;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

@Controller
public class HomePageController {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public Mono<Rendering> getHomePage(){
        return Mono.just(Rendering.redirectTo("users/").build());
    }
}
