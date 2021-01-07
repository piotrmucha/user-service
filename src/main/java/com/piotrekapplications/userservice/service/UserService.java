package com.piotrekapplications.userservice.service;

import com.piotrekapplications.userservice.entity.User;
import com.piotrekapplications.userservice.entity.UserDto;
import com.piotrekapplications.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean addUser(UserDto userDto)  {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setIsAdministrator(userDto.isAdministrator());
        user.setIsConfirmed(false);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        try {
            userRepository.save(user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void confirmUser(String email) {
        User user = userRepository.getUserByEmail(email);
        user.setIsConfirmed(true);
        userRepository.save(user);
    }

    public boolean userExists(String email) {
        User user = userRepository.getUserByEmail(email);
        return user != null;
    }

    public boolean correctPassword(String email, String password) {
        User user = userRepository.getUserByEmail(email);
        if (user!= null) {
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }

    public boolean isUserConfirmed(String email) {
        User user = userRepository.getUserByEmail(email);
        if (user!= null) {
            return user.getIsConfirmed();
        }
        return false;
    }

    public boolean isAdministrator(String mail) {
        User user = userRepository.getUserByEmail(mail);
        if (user!= null) {
            return user.getIsAdministrator();
        }
        return false;
    }
}
