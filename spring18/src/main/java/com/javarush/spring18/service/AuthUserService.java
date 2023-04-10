package com.javarush.spring18.service;

import com.javarush.spring18.repository.Repo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
//18 lesson
public class AuthUserService implements UserDetailsService {

    private final Repo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo.getUserByLogin(username).map(
                u -> {
                    log.info("=================== " + u.getRole());
                    return new User(
                            u.getLogin(),
                            u.getPassword().startsWith("{")
                                    ? u.getPassword()
                                    : "{noop}" + u.getPassword(),
                            List.of(u.getRole())
                    );
                }
        ).orElseThrow(() -> new UsernameNotFoundException("user %s not found".formatted(username)));
    }
}
