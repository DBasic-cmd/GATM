package com.example.gatm.Repository.auth;

import com.example.gatm.model.auth.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserTokenRepository extends JpaRepository<UserToken, Long> {
}
