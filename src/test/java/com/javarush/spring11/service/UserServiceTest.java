package com.javarush.spring11.service;

import com.javarush.spring11.entity.User;
import com.javarush.spring11.repository.Repo;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class UserServiceTest {

    public static final long USER_ID = 1L;
    public static final long USER_DB = 2L;
    public static final int ONE_TIMES = 1;

    @Mock
    private Repo repo;

    @InjectMocks
    private UserService userService;
    private User dbUser;

    @BeforeEach
    void setup() {
        dbUser = User.builder()
                .id(USER_DB)
                .login("mockUser")
                .password("mockPassword")
                .build();
    }

    @Test
    void get() {
        doAnswer(invocation -> Optional.ofNullable(dbUser))
                .when(repo)
                .findById(USER_ID);
        User user = userService.get(USER_ID).orElseThrow();
        assertEquals(USER_DB, user.getId());
        verify(repo, times(ONE_TIMES)).findById(USER_ID);
        verifyNoMoreInteractions(repo);
    }

    @Test
    void findAll() {
        Sort sort=Sort.sort(User.class).by(User::getId);
        doAnswer(invocation -> Collections.singletonList(dbUser))
                .when(repo)
                .findAll(sort);
        List<User> all = userService.findAll();
        assertEquals(1, all.size());
        assertEquals("mockUser", all.get(0).getLogin());
        verify(repo, times(ONE_TIMES)).findAll(sort);
    }

    @Test
    void findById() {
        doAnswer(invocation -> Optional.ofNullable(dbUser))
                .when(repo)
                .findById(USER_ID);
        User user = userService.findById(USER_ID).orElseThrow();
        assertEquals(USER_DB, user.getId());
        verify(repo, times(ONE_TIMES)).findById(USER_ID);
        verifyNoMoreInteractions(repo);
    }

    @Test
    void save() {
        doAnswer(invocation -> dbUser).when(repo).saveAndFlush(dbUser);
        User user = userService.save(dbUser);
        assertEquals(USER_DB,user.getId());
        verify(repo,times(ONE_TIMES)).saveAndFlush(dbUser);
        verifyNoMoreInteractions(repo);

    }

    @Test
    void delete() {
        doNothing().when(repo).delete(dbUser);
        userService.delete(dbUser);
        verify(repo,times(ONE_TIMES)).delete(dbUser);
        verifyNoMoreInteractions(repo);
    }

    @Test
    void deleteById() {
        doNothing().when(repo).deleteById(USER_ID);
        userService.deleteById(USER_ID);
        verify(repo,times(ONE_TIMES)).deleteById(USER_ID);
        verifyNoMoreInteractions(repo);
    }
}