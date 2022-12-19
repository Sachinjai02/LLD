package com.study.splitwise.dtos;

public class RegisterUserResponseDto<T> extends BaseResponseDto<T> {
    public RegisterUserResponseDto(T data) {
        super(data);
    }

    public RegisterUserResponseDto() {

    }
}
