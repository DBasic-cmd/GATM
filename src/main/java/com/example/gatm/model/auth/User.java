package com.example.gatm.model.auth;

import com.example.gatm.model.auth.enums.SecurityQuestion;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    private boolean isDisabled;
    private boolean isAccountVerified;
    private Date createdAt;
    private Date modifiedAt;
    @Enumerated(EnumType.STRING)
    private SecurityQuestion securityQuestion;
    private String securityAnswer;
    @ManyToOne(targetEntity = UserRole.class, fetch = FetchType.LAZY)
    private UserRole role;
    @Transient
    private String fullName;
    @JsonDeserialize
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @JsonSerialize
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
