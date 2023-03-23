package com.javarush.spring12.integration;

import com.javarush.spring12.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestRestTemplateIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testGet() {
        User user = testRestTemplate.getForObject("/api/rest/v1/users/1", User.class);
        assertEquals("Ivan", user.getLogin());
    }

    @Test
    void testPost() {
        User user = User.builder().login("hello").password("world").build();
        ResponseEntity<User> response = testRestTemplate.postForEntity("/api/rest/v1/users/", user, User.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        User body = response.getBody();
        assertNotNull(body);
        assertThat(body.getId()).isGreaterThan(0L);
        assertThat(body.getLogin()).isEqualTo(user.getLogin());
        assertThat(body.getPassword()).isEqualTo(user.getPassword());
        testRestTemplate.delete("/api/rest/v1/users/{id}", body.getId(), body);
    }

    @Test
    void testPut() {
        User user = User.builder().login("hello").password("world").build();
        ResponseEntity<User> response = testRestTemplate.postForEntity("/api/rest/v1/users/", user, User.class);
        user=response.getBody();
        assertNotNull(user);
        user.setLogin("Hello");
        user.setPassword("World");
        testRestTemplate.put("/api/rest/v1/users/{id}", user, response.getBody().getId());
        User updatedUser = testRestTemplate.getForObject("/api/rest/v1/users/" + response.getBody().getId(), User.class);
        assertThat(updatedUser.getLogin()).isEqualTo("Hello");
        assertThat(updatedUser.getPassword()).isEqualTo("World");
        testRestTemplate.delete("/api/rest/v1/users/{id}", updatedUser.getId(), updatedUser);

    }

    @Test
    void testDelete() {
        User user = User.builder().login("hello").password("world").build();
        ResponseEntity<User> response = testRestTemplate.postForEntity("/api/rest/v1/users/", user, User.class);
        assertNotNull(response.getBody());
        testRestTemplate.delete("/api/rest/v1/users/{id}", response.getBody().getId(), response.getBody());
        ResponseEntity<User> deletedUser = testRestTemplate.getForEntity("/api/rest/v1/users/{id}", User.class, response.getBody().getId());
        assertThat(deletedUser.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
