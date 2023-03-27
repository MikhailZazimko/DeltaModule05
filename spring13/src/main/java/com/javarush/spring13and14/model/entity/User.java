package com.javarush.spring13and14.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "t_user")
@ToString
public class User {

    @Id
    private Long id;

    private String login;

    private String password;

}
