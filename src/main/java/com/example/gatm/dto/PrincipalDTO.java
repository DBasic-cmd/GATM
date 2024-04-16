package com.example.gatm.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PrincipalDTO {
    private String message;
    private boolean isSuccessful;
    private String token;


}
