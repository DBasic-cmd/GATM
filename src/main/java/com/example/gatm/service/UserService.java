package com.example.gatm.service;

import com.example.gatm.dto.PrincipalDTO;
import com.example.gatm.dto.UserDto;
import com.example.gatm.dto.UserLoginDto;
import com.example.gatm.dto.UserSignUpDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto getUserById(Long userId);

    UserDto signUpUser(UserSignUpDto signUpDto);
    UserDto adminSignUp(UserDto dto);

    PrincipalDTO loginUser(UserLoginDto dto);

    UserDto updateUser(Long userId, UserDto userDto);

    void deleteUser(Long userId);
}
