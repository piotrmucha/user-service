package com.piotrekapplications.userservice.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String email;
    private String password;
    private boolean administrator;
}
