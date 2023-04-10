package com.javarush.spring18.service;

import com.javarush.spring18.entity.User;
import com.javarush.spring18.repository.Repo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final Repo repo;

    //18 lesson
    private final PasswordEncoder passwordEncoder;

    public Optional<User> get(Long id) {
        return repo.findById(id);
    }

    public List<User> findAll() {
        Sort sort = Sort.sort(User.class).by(User::getId);
        return repo.findAll(sort);
    }

    @Transactional
    public User save(User user) {
        //18 lesson
        if (user.getPassword().length() > 3) {
            String encode = passwordEncoder.encode(user.getPassword());
            user.setPassword(encode);
        }
        return repo.saveAndFlush(user);
    }

    @Transactional
    public void delete(User user) {
        repo.delete(user);
    }

    @Transactional
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

}
