package com.javarush.spring13and14.model.dto;

import lombok.Data;

@Data
public class UserFormData {

    Long id;
    String login;
    String password;

    String operation;
}
