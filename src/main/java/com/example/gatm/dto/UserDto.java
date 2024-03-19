package com.example.gatm.dto;

import com.example.gatm.model.auth.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private UserRole role;

}
