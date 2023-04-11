package com.javarush.spring18.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, GUEST;

    //18 lesson
    @Override
    public String getAuthority() {
        // For correct working springframework.security need ROLE_ADMIN instead of ADMIN.
        // Strange, but this is the story ;)
        return this.toString();
    }
}
