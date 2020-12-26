package com.piotrekapplications.userservice.service;

import com.piotrekapplications.userservice.entity.User;
import com.piotrekapplications.userservice.entity.UserDto;
import com.piotrekapplications.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setIsAdministrator(false);
        user.setIsConfirmed(false);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
    }

    public void confirmUser(String email) {
        User user = userRepository.getUserByEmail(email);
        user.setIsConfirmed(true);
        userRepository.save(user);
    }
}
