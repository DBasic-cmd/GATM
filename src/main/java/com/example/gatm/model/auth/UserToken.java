package com.example.gatm.model.auth;

import com.example.gatm.model.auth.enums.TokenType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class UserToken {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;
    private LocalDateTime expiryTime;
    private TokenType tokenType;
}
