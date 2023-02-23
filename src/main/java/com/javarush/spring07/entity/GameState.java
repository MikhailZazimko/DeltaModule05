package com.javarush.spring07.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "t_game_state")
public class GameState {
    @Id
    @Column(name = "value", nullable = false, length = 64)
    private String id;

    //TODO [JPA Buddy] generate columns from DB
}