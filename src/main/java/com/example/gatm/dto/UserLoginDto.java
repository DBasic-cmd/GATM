package com.example.gatm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserLoginDto {
    private String email;
    private String password;
}
