package com.piotrekapplications.userservice.web;

import com.piotrekapplications.userservice.entity.UserDto;
import com.piotrekapplications.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createUser(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
    }
    @PutMapping("/confirmation/{email}")
    public void confirmUser(@PathVariable String email) {
        userService.confirmUser(email);
    }
}
