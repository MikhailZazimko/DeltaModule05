package com.javarush.spring13and14.controller.rest;

import com.javarush.spring13and14.model.dto.UserDto;
import com.javarush.spring13and14.model.entity.User;
import com.javarush.spring13and14.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(UserRestController.ENDPOINT)
public class UserRestController {

    public static final String ENDPOINT = "/api/rest/v1/users";
    private final UserService userService;

    //READ
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<UserDto> findAll() {
        return userService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public Mono<UserDto> get(@PathVariable Long id) {
        return userService.get(id);

    }

    //WRITING
    //CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> create(@RequestBody UserDto userDto) {
            return userService.save(userDto);
    }

    //UPDATE
    @PutMapping("/{id}") //or @PatchMapping (if only part data update)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<User> update(@PathVariable Long id, @RequestBody UserDto userDto) {
            return userService.save(userDto);
    }

    //DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable Long id) {
            return userService.deleteById(id);
    }

}
