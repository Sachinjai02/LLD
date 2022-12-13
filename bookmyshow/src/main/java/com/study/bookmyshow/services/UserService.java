package com.study.bookmyshow.services;

import com.study.bookmyshow.models.User;
import com.study.bookmyshow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User createUser(String userName, String emailId) {
        User user = new User();
        user.setName(userName);
        user.setEmail(emailId);
        user = userRepository.save(user);
        return user;
    }
}
