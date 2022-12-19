package com.study.splitwise.services;

import com.study.splitwise.exceptions.UserNotFoundException;
import com.study.splitwise.models.User;
import com.study.splitwise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User registerUser(String userName, String phone, String password) {
        User user = new User();
        user.setName(userName);
        user.setPhoneNumber(phone);
        user.setPwd(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    public boolean login(String phone, String password) {
        Optional<User> user = userRepository.findByPhoneNumber(phone);
        if(! user.isPresent()) {
            throw new UserNotFoundException("No user found with given phone number");
        }
        return passwordEncoder.matches(password, user.get().getPwd());
    }

    public void update(Long userId, String newPassword) {
        Optional<User> user = userRepository.findById(userId);
        if(! user.isPresent()) {
            throw new UserNotFoundException("No user found with given Id");
        }
        user.get().setPwd(passwordEncoder.encode(newPassword));
        userRepository.save(user.get());
    }
}
