package com.javarush.spring07.repository;

import com.javarush.spring07.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Context
class RepoTest {

    @Autowired
    Repo repo;

    @Test
    void getById() {
        User user = repo.getById(1L);
        assertEquals(1L, user.getId());
    }

    @Test
    void findUserByLogin() {
        User user = repo.findUserByLogin("Iva", "an").get(0);
        assertEquals(1L, user.getId());
    }

    @Test
    void findUsersByLogin() {
        List<User> users = repo.findUserByLogin("", "a");
        assertEquals(4, users.size());
    }

    @Test
    void findAllByPage() {
        Sort.TypedSort<User> sort = Sort.sort(User.class);
        Sort orders = sort.by(User::getId).descending();

        Pageable pageable = PageRequest.of(0, 3, orders);
        Page<User> page;
        do {
            page = repo.findAllBy(pageable);
            pageable=pageable.next();
            page.forEach(System.out::println);
            System.out.printf("Page %d from %d (items %d)\n"
                    , page.getNumber(), page.getTotalPages(), page.getTotalElements());
        } while (page.hasNext());
        assertEquals(7, page.getTotalElements());

    }
}