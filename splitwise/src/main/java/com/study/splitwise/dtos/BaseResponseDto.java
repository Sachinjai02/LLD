package com.study.splitwise.dtos;

import lombok.Data;

@Data
public class BaseResponseDto<T>{
    private T data;
    private String status;
    private String message;

    public BaseResponseDto(T data) {
        this.data = data;
    }

    public BaseResponseDto() {

    }

    public BaseResponseDto(T data, String status) {
        this(data);
        this.status = status;

    }



}
