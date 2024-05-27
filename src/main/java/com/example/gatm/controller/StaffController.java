package com.example.gatm.controller;

import com.example.gatm.Repository.auth.UserRepository;
import com.example.gatm.Repository.auth.UserRoleRepository;
import com.example.gatm.dto.StaffDto;
import com.example.gatm.dto.UserDto;
import com.example.gatm.model.auth.User;
import com.example.gatm.model.auth.UserRole;
import com.example.gatm.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/staff")
public class StaffController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    public StaffController(UserService userService, UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<User> createStaff(@RequestBody StaffDto staffCreationDto) {
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean isAdmin = false;
        UserDetails userDetails = (UserDetails) user;
        Optional<User> optionalUser = userRepository.findByEmail(userDetails.getUsername());
        User user1 = optionalUser.get();
        Optional<UserRole> optionalUserRole = userRoleRepository.findById(user1.getRole().getId());
        if (optionalUserRole.get().getName().equals("ADMIN")) {
            isAdmin = true;
        }
        if (isAdmin) {
            User createdStaff = userService.createStaff(staffCreationDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdStaff);
        }
        throw new RuntimeException("User is not authorized");

    }
}
