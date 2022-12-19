package com.study.splitwise.dtos;

public class RegisterUserResponseDto<T> extends BaseResponseDto {
    public RegisterUserResponseDto(T data) {
        super(data);
    }

    public RegisterUserResponseDto() {

    }
}
