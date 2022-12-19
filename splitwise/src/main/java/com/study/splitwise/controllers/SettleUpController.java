package com.study.splitwise.controllers;

import com.study.splitwise.controllers.pojos.Transaction;
import com.study.splitwise.dtos.BaseResponseDto;
import com.study.splitwise.services.SettleUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SettleUpController {
    private SettleUpService settleUpService;

    @Autowired
    public SettleUpController(SettleUpService settleUpService) {
        this.settleUpService = settleUpService;
    }

    public BaseResponseDto<List<Transaction>> settleUser(Long userId) {
        BaseResponseDto<List<Transaction>> responseDto = new BaseResponseDto<>();
        try {
            List<Transaction> transactionsToSettleUp = settleUpService.settleUser(userId);
            responseDto.setStatus("Success");
            responseDto.setData(transactionsToSettleUp);
        } catch (Exception e) {
            e.printStackTrace();
            responseDto.setStatus("Failed");
            responseDto.setMessage(e.getMessage());
        }

        return responseDto;
    }

    public BaseResponseDto<List<Transaction>> settleGroup(Long groupId) {
        BaseResponseDto<List<Transaction>> responseDto = new BaseResponseDto<>();
        try {
            List<Transaction> transactionsToSettleUp = settleUpService.settleGroup(groupId);
            responseDto.setStatus("Success");
            responseDto.setData(transactionsToSettleUp);
        } catch (Exception e) {
            e.printStackTrace();
            responseDto.setStatus("Failed");
            responseDto.setMessage(e.getMessage());
        }

        return responseDto;
    }
}
