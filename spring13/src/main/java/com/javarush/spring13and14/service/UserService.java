package com.javarush.spring13and14.service;

import com.javarush.spring13and14.model.dto.UserDto;
import com.javarush.spring13and14.model.entity.User;
import com.javarush.spring13and14.model.dto.UserFormData;
import com.javarush.spring13and14.model.mapper.UserMapper;
import com.javarush.spring13and14.repository.Repo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class UserService {

    private final Repo repo;

    private final UserMapper userMapper;

    public Mono<UserDto> get(Long id) {
        return repo.findById(id).map(userMapper::dto);
    }

    public Flux<UserDto> findAll() {
        return repo.findAll()
                .map(userMapper::dto)
                //.delayElements(Duration.of(1, ChronoUnit.SECONDS))
                ;
    }

    public Mono<UserDto> findById(Long id) {
        return repo.findById(id).map(userMapper::dto);
    }

    public Mono<User> save(UserDto userDto) {
        return repo.save(userMapper.from(userDto));
    }

    public Mono<Void> delete(UserFormData userFormData) {
        return repo.delete(userMapper.from(userFormData));
    }

    public Mono<Void> deleteById(Long id) {
        return repo.deleteById(id);
    }

    public Mono<User> save(UserFormData userFormData) {
        return repo.save(userMapper.from(userFormData));
    }
}
