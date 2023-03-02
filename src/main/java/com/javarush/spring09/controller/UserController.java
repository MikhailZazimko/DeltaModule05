package com.javarush.spring09.controller;

import com.javarush.spring09.entity.User;
import com.javarush.spring09.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public ModelAndView showUsers(ModelAndView view) {
        log.info("users");
        List<User> users = userService.findAll();
        view.addObject("users", users);
        view.setViewName("userpage");
        return view;
    }

    @GetMapping("/{id}/")
    public ModelAndView showUserForm(ModelAndView view,
                                     @PathVariable Long id) {
        log.info("get user {}", id);
        view
                .addObject("users", userService.findAll())
                .addObject("user", userService.get(id).orElseThrow())
                .setViewName("userpage");
        return view;
    }

    @PostMapping("/{id}/")
    public String modifyUser(User user,
                             @PathVariable Long id,
                             @RequestParam(required = false) String deleteUser,
                             HttpServletRequest request) {
        log.info("post user={}", deleteUser);
        if (user.getId().equals(id)) {
            if (Objects.nonNull(deleteUser)) {
                userService.delete(user);
                return "redirect:/users/";
            } else {
                userService.save(user);
                return "redirect:/users/%d/".formatted(id);
            }
        } else {
            throw new RuntimeException("incorrect id");
        }

    }

    @PostMapping("/")
    public String newUser(User user,
                          @RequestParam(required = false) String loginUser,
                          HttpSession session) {
        log.info("post user={}", user);
        if (Objects.nonNull(loginUser)) {
            Optional<User> optionalUser = userService.findByLoginAndPassword(user.getLogin(), user.getPassword());
            if (optionalUser.isPresent()){
                user=optionalUser.get();
                session.setAttribute("user", user);
            }
        } else {
            userService.save(user);
        }
        return "redirect:/users/%d/".formatted(user.getId());
    }

}
