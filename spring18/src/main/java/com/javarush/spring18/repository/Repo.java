package com.javarush.spring18.repository;

import com.javarush.spring18.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Repo extends JpaRepository<User, Long> {

    Optional<User> getUserByLogin(String login);
}
