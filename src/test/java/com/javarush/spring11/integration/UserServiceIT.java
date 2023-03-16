package com.javarush.spring11.integration;

import com.javarush.spring11.entity.User;
import com.javarush.spring11.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestContext
@RequiredArgsConstructor
class UserServiceIT {

    public static final long USED_ID = 1L;
    public static final int COUNT_USER_IN_DB = 4;
    private final UserService userService;
    private final JdbcTemplate jdbcTemplate;


    @Test
    void jdbcCountUser(){
        int count = JdbcTestUtils.countRowsInTable(jdbcTemplate, "t_user");
        assertEquals(COUNT_USER_IN_DB,count);
    }

    @Test
    void get() {
        User user = userService.get(USED_ID).orElseThrow();
        assertEquals(USED_ID, user.getId());
        assertEquals("Ivan", user.getLogin());
        assertEquals("456", user.getPassword());
    }

    @Test
    void findAll() {
        List<User> all = userService.findAll();
        assertEquals(COUNT_USER_IN_DB, all.size());
    }

    @Test
    void findById() {
        User user = userService.findById(USED_ID).orElseThrow();
        assertEquals(USED_ID, user.getId());
        assertEquals("Ivan", user.getLogin());
        assertEquals("456", user.getPassword());
    }

    @Test
    void save() {
        User user = User.builder()
                .login("mockUser")
                .password("mockPassword")
                .build();
        userService.save(user);
        assertTrue(user.getId() > 0);

    }

    @Test
    void delete() {
        User user = User.builder()
                .id(1L)
                .login("Ivan")
                .password("456")
                .build();
        userService.delete(user);
        List<User> all = userService.findAll();
        assertEquals(COUNT_USER_IN_DB-1, all.size());
    }

    @Test
    @Sql(statements = "DELETE FROM t_user WHERE id=2")
    void deleteById() {
        userService.deleteById(USED_ID);
        List<User> all = userService.findAll();
        assertEquals(COUNT_USER_IN_DB-2, all.size());
    }

}