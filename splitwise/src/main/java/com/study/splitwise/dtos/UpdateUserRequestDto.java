package com.study.splitwise.dtos;

import lombok.Data;

@Data
public class UpdateUserRequestDto {
    private Long userId;
    private String newPassword;
}
