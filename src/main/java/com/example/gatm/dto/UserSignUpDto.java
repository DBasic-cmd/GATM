package com.example.gatm.dto;

import com.example.gatm.model.auth.enums.SecurityQuestion;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserSignUpDto implements Serializable {
    private static final long serialVersionUID = 9012029399082280379L;
    public String password;
    public SecurityQuestion securityQuestion;
    public String securityAnswer;

}
