package com.javarush.spring13and14.model.dto;

import lombok.Data;

@Data
public class UserDto {

    Long id;
    String login;

    String password; //need delete this field
}
