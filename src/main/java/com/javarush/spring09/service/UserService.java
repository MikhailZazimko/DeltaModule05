package com.javarush.spring09.service;

import com.javarush.spring09.entity.User;
import com.javarush.spring09.repository.Repo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final Repo repo;

    public Optional<User> get(Long id) {
        return repo.findById(id);
    }

    public List<User> findAll() {
        return repo.findAll();
    }

    @Transactional
    public User save(User user) {
        repo.save(user);
        return user;
    }

    @Transactional
    public void delete(User user) {
        repo.delete(user);
    }

    public Optional<User> findById(Long id) {
        return repo.findById(id);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    public Optional<User> findByLoginAndPassword(String login, String password) {
        return repo.findByLoginAndPassword(login, password);
    }
}
