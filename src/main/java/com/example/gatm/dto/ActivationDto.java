package com.example.gatm.dto;

import lombok.Data;

@Data
public class ActivationDto {
    private String password;
    private String confirmPassword;
    private String otp;
}
