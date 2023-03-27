package com.javarush.spring13and14.controller.html;

import com.javarush.spring13and14.model.dto.UserFormData;
import com.javarush.spring13and14.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@SessionAttributes({"currentUser"})
public class UserController {

    public static final String USERS_PAGE = "/users/";
    public static final String DELETE = "delete";
    public static final String USERS = "users";
    public static final String USER = "user";
    public static final String USER_PAGE = "/users/%d/";
    public static final String USER_VIEW = "user_page";

    private final UserService userService;

    private static Rendering redirect(String url) {
        return Rendering.redirectTo(url).build();
    }

    @GetMapping("/")
    public Mono<Rendering> showAllUsers() {
        return Mono.just(Rendering
                .view(USER_VIEW)
                .modelAttribute(USERS, userService.findAll())
                .build()
        );
    }

    @GetMapping("/{id}/")
    public Mono<Rendering> showOneUserAndUsers(@PathVariable Long id) {
        return Mono.just(Rendering
                .view(USER_VIEW)
                .modelAttribute(USERS, userService.findAll())
                .modelAttribute(USER, userService.findById(id))
                .build()
        );
    }

    @PostMapping("/")
    public Mono<Rendering> processNewUserOrLogin(UserFormData userFormData) {
        return userService.save(userFormData)
                .map(u -> redirect(USER_PAGE.formatted(u.getId())))
                .doOnError(e -> redirect(USERS_PAGE));
    }

    @PostMapping("/{id}/")
    public Mono<Rendering> updateOrDeleteUser(UserFormData userFormData) {
        return !DELETE.equals(userFormData.getOperation())
                ? userService
                .save(userFormData)
                .map(u -> redirect(USER_PAGE.formatted(u.getId())))
                .doOnError(e -> redirect(USERS_PAGE))
                : userService
                .delete(userFormData)
                .thenReturn(redirect(USERS_PAGE));
    }
}
