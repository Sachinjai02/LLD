package com.study.splitwise.dtos;

import lombok.Data;

@Data
public class RegisterUserRequestDto {
    private String userName;
    private String phoneNumber;
    private String password;
}
