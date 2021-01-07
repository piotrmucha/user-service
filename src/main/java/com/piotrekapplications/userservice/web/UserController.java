package com.piotrekapplications.userservice.web;

import com.piotrekapplications.userservice.entity.UserDto;
import com.piotrekapplications.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {
        boolean result = userService.addUser(userDto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PutMapping("/confirmation/{email}")
    public void confirmUser(@PathVariable String email) {
        userService.confirmUser(email);
    }

    @GetMapping("/confirmation/{email}")
    public ResponseEntity<String> isUserConfirmed(@PathVariable String email) {
        boolean confirmed = userService.isUserConfirmed(email);
        if (confirmed) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/{mail}")
    public ResponseEntity<String> verifyUser(@PathVariable("mail") String mail) {
        boolean result = userService.userExists(mail);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/password")
    public ResponseEntity<String> verifyPassword(@RequestParam("mail") String mail, @RequestParam("password") String password) {
        boolean result = userService.correctPassword(mail, password);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
    @GetMapping("/administrator")
    public ResponseEntity<String> isAdministrator(@RequestParam("mail") String mail) {
        boolean result = userService.isAdministrator(mail);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
