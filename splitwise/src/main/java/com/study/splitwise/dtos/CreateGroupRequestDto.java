package com.study.splitwise.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CreateGroupRequestDto {
    private String name;
    private String desc;
    private Long createdByUserId;
    private List<Long> members;

}
