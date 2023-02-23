package com.javarush.spring07.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "t_user_info")
public class TUserInfo {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 128)
    @Column(name = "address", length = 128)
    private String address;

    @Size(max = 128)
    @Column(name = "phone", length = 128)
    private String phone;

    @Column(name = "user_id")
    private Long userId;

}