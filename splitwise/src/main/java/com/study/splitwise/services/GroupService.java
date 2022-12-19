package com.study.splitwise.services;

import com.study.splitwise.models.Group;
import com.study.splitwise.models.User;
import com.study.splitwise.repositories.GroupRepository;
import com.study.splitwise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    private GroupRepository groupRepository;
    private UserRepository userRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    public Group createGroup(String name, String desc, List<Long> members, Long createdByUserId) {
        Group group = new Group();
        group.setName(name);
        group.setDescription(desc);
        group.setCreatedAt(new Date());
        group.setUsers(userRepository.findAllById(members));
        Optional<User> createdByUser = userRepository.findById(createdByUserId);
        group.setCreatedBy(createdByUser.get());
        return groupRepository.save(group);
    }
}
