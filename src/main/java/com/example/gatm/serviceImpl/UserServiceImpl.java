package com.example.gatm.serviceImpl;

import com.example.gatm.Repository.auth.UserRepository;
import com.example.gatm.Repository.auth.UserRoleRepository;
import com.example.gatm.dto.PrincipalDTO;
import com.example.gatm.dto.UserDto;
import com.example.gatm.dto.UserLoginDto;
import com.example.gatm.dto.UserSignUpDto;
import com.example.gatm.exception.UserNotFoundException;
import com.example.gatm.model.auth.User;
import com.example.gatm.model.auth.UserRole;
import com.example.gatm.security.JWTUtil;
import com.example.gatm.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    @Override
    public List<UserDto> getAllUsers() {
        log.info("Fetching all users...");
        List<User> userList = userRepository.findAll();
        List<UserDto> responseList = new ArrayList<>();
        for (User user : userList) {
            UserDto response = new UserDto();
            response.setFirstName(user.getFirstName());
            response.setLastName(user.getLastName());
            response.setEmail(user.getEmail());
//            response.setRole(user.getRole());
            responseList.add(response);
        }
        log.info("Fetched {} users", responseList.size());
        return responseList;
    }

    @Override
    public UserDto getUserById(Long userId) {
        log.info("Fetching user by ID: {}", userId);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setEmail(user.getEmail());
//            userDto.setRole(user.getRole());
            return userDto;
        } else {
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
    }



    @Override
    public UserDto signUpUser(UserSignUpDto signUpDto) {
        User newUser = new User();
        newUser.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        newUser.setSecurityQuestion(signUpDto.getSecurityQuestion());
        newUser.setSecurityAnswer(signUpDto.getSecurityAnswer());
        User savedUser = userRepository.save(newUser);

        UserDto userDto = new UserDto();
        userDto.setId(savedUser.getId());
        userDto.setFirstName(savedUser.getFirstName());
        userDto.setLastName(savedUser.getLastName());
        userDto.setEmail(savedUser.getEmail());

        return userDto;
    }

    @Override
    public UserDto adminSignUp(UserDto dto) {
        Optional<User> optionalUser = userRepository.findUserByEmail(dto.getEmail());
        if (optionalUser.isEmpty()){
            User user = new User();
            user.setEmail(dto.getEmail());
            user.setFirstName(dto.getFirstName());
            user.setLastName(dto.getLastName());
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
            user.setDisabled(true);
            Optional<UserRole> role = userRoleRepository.findById(dto.getRoleId());
            if (dto.getRoleId() != null && role.isPresent()){
                user.setRole(role.get());
            }
            userRepository.save(user);
        }else {
            throw new RuntimeException("Email already exist");
        }
        return null;
    }

    @Override
    public PrincipalDTO loginUser(UserLoginDto dto) {
//        Authentication authentication;
//        PrincipalDTO principalDTO = new PrincipalDTO();
//        try{
//            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
//        }catch (AuthenticationException ex){
//            log.info(ex.getMessage());
//            System.out.println("Exceppppppppppppppppp " + dto.getEmail());
//            principalDTO.setMessage("Not Successful");
//            principalDTO.setSuccessful(false);
//            return principalDTO;
//        }
//
//        principalDTO.setMessage("Successful");
//        principalDTO.setSuccessful(true);
//        principalDTO.setToken(jwtUtil.generateToken(dto.getEmail()));
        PrincipalDTO principalDTO = new PrincipalDTO();
        Optional<User> optionalUser = userRepository.findByEmail(dto.getEmail());
        if (optionalUser.isPresent()){

            if (passwordEncoder.matches(dto.getPassword(), optionalUser.get().getPassword())){
                principalDTO.setMessage("Successful");
                principalDTO.setSuccessful(true);
                principalDTO.setToken(jwtUtil.generateToken(dto.getEmail()));
            }
        }else {
            log.info("User not found");
            principalDTO.setMessage("Not Successful");
            principalDTO.setSuccessful(false);
        }

//        SecurityContextHolder.getContext().setAuthentication(authentication);


        return principalDTO;

    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            // Update the fields of the existing user with the values from userDto
            existingUser.setFirstName(userDto.getFirstName());
            existingUser.setLastName(userDto.getLastName());
            existingUser.setEmail(userDto.getEmail());

            // Save the updated user to the repository
            User updatedUser = userRepository.save(existingUser);

            // Convert the updated user entity to a UserDto and return it
            UserDto updatedUserDto = new UserDto();
            updatedUserDto.setId(updatedUser.getId());
            updatedUserDto.setFirstName(updatedUser.getFirstName());
            updatedUserDto.setLastName(updatedUser.getLastName());
            updatedUserDto.setEmail(updatedUser.getEmail());

            return updatedUserDto;
        } else {
            // Handle the case where user with given userId is not found
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
    }

    @Override
    public void deleteUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            userRepository.delete(user);
        } else {
            // Handle the case where user with given userId is not found
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
    }

}




