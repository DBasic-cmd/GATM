package com.example.gatm.service;

import com.example.gatm.dto.*;
import com.example.gatm.model.auth.User;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto getUserById(Long userId);

    UserDto signUpUser(UserSignUpDto signUpDto);
    UserDto adminSignUp(UserDto dto);

    PrincipalDTO loginUser(UserLoginDto dto);

    UserDto updateUser(Long userId, UserDto userDto);

    void deleteUser(Long userId);

    User createStaff(StaffDto staffCreationDto);

    String activateAccount(ActivationDto activationDto, String email);
}
