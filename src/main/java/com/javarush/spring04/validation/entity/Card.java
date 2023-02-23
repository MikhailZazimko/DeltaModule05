package com.javarush.spring04.validation.entity;

import com.javarush.spring04.validation.custom.CheckCode;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@Builder
@CheckCode
public class Card {

    @Size(min = 16, max = 32)
    private String rawCardNumber;

    @Positive
    private Integer bankIdNo;

    @NotNull
    private Integer accountNo;

    @Positive
    @NotNull
    private Integer checkCode;

}