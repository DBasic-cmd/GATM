package com.example.gatm.model.auth;

import com.example.gatm.model.auth.enums.SecurityQuestion;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    private String password;
    private boolean isDisabled;
    private boolean isAccountVerified;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
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
