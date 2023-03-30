package com.javarush.spring13.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.spring13and14.controller.rest.UserRestController;
import com.javarush.spring13and14.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest(
        classes = com.javarush.spring13and14.AppTestLesson13and14.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class UserRestControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    WebTestClient webClient;

    @Test
    void findAll() {
        webClient.get().uri(UserRestController.ENDPOINT)
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(User.class);
    }

    @Test
    void get() {
        webClient.get().uri(UserRestController.ENDPOINT + "/1")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(User.class);
    }

    @Test
    void createAndUpdateAndDelete() throws JsonProcessingException {
        User user = User.builder()
                .login("test_user" + System.currentTimeMillis())
                .password("test_password")
                .build();

        byte[] responseBody = webClient.post().uri(UserRestController.ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), User.class)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.login").isEqualTo(user.getLogin())
                .jsonPath("$.password").isEqualTo(user.getPassword())
                .returnResult()
                .getResponseBody();
        assert responseBody != null;
        User newUser = objectMapper.readValue(new String(responseBody), User.class);

        newUser.setPassword("hello");
        webClient.put().uri(UserRestController.ENDPOINT + "/" + newUser.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(newUser), User.class)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.login").isEqualTo(newUser.getLogin())
                .jsonPath("$.password").isEqualTo(newUser.getPassword());

        webClient.delete().uri(UserRestController.ENDPOINT + "/" + newUser.getId())
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().is2xxSuccessful();
    }
}