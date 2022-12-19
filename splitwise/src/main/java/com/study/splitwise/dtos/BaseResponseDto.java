package com.study.splitwise.dtos;

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


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
