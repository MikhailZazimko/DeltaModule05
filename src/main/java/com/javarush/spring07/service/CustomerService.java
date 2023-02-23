package com.javarush.spring07.service;

import com.javarush.spring07.entity.User;
import com.javarush.spring07.repository.Repo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private final Repo repo;

    public Optional<User> get(Long id) {
        return repo.findById(id);
    }

    public void update(User user) {
        repo.save(user);
    }

}
