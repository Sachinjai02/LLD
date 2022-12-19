package com.study.splitwise.controllers;

import com.study.splitwise.dtos.BaseResponseDto;
import com.study.splitwise.dtos.CreateGroupRequestDto;
import com.study.splitwise.models.Group;
import com.study.splitwise.services.GroupService;
import org.springframework.stereotype.Controller;

@Controller
public class GroupController {

    private GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    public BaseResponseDto<Group> createGroup(CreateGroupRequestDto groupRequestDto) {
        Group group = groupService.createGroup(groupRequestDto.getName(), groupRequestDto.getDesc(), groupRequestDto.getMembers(), groupRequestDto.getCreatedByUserId());
        BaseResponseDto<Group> responseDto = new BaseResponseDto<>(group);
        responseDto.setStatus("Success");
        return responseDto;
    }
}
