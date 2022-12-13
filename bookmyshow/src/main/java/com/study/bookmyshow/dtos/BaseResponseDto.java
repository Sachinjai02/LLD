package com.study.bookmyshow.dtos;

import lombok.Data;

@Data
public class BaseResponseDto<T> {
    private ResponseStatus status;
    private String message;
    private T data;
}
