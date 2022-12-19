package com.study.splitwise.controllers;

import com.study.splitwise.dtos.BaseResponseDto;
import com.study.splitwise.dtos.RegisterUserRequestDto;
import com.study.splitwise.dtos.RegisterUserResponseDto;
import com.study.splitwise.dtos.UpdateUserRequestDto;
import com.study.splitwise.models.User;
import com.study.splitwise.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public RegisterUserResponseDto<User> registerUser(RegisterUserRequestDto requestDto) {
        RegisterUserResponseDto<User> responseDto = new RegisterUserResponseDto<>();
        try {
            responseDto = new RegisterUserResponseDto(this.userService.registerUser(requestDto.getUserName(), requestDto.getPhoneNumber(), requestDto.getPassword()));
            responseDto.setStatus("Success");
        }catch (Exception e) {
            responseDto.setMessage(e.getMessage());
            responseDto.setStatus("Failed");
        }
        return responseDto;
    }

    public BaseResponseDto<Boolean> login(String phoneNumber, String password) {
        BaseResponseDto<Boolean> responseDto = new BaseResponseDto<>();
        try {
            if(this.userService.login(phoneNumber, password)) {
                responseDto.setStatus("Success");
            } else {
                responseDto.setStatus("Invalid Auth");
                responseDto.setMessage("User authentication details are not valid");
            }
        } catch (Exception e) {
            responseDto.setMessage("Could not authenticate : " + e.getMessage());
            responseDto.setStatus("Failed");
        }
        return responseDto;
    }

    public BaseResponseDto<Boolean> updateProfile(UpdateUserRequestDto requestDto) {
        BaseResponseDto<Boolean> responseDto = new BaseResponseDto<>(true, "Success");
        try {
            this.userService.update(requestDto.getUserId(), requestDto.getNewPassword());
        }catch (Exception e) {
            responseDto.setMessage(e.getMessage());
            responseDto.setStatus("Failed");
        }
        return responseDto;
    }
}
