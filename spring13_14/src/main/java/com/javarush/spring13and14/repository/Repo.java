package com.javarush.spring13and14.repository;

import com.javarush.spring13and14.model.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface Repo extends ReactiveCrudRepository<User, Long> {

}
