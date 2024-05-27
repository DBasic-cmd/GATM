package com.example.gatm.controller;

import com.example.gatm.dto.EmailDto;
import com.example.gatm.service.EmailService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/mail")
public class EmailSendController {
    private EmailService emailService;

    public EmailSendController(EmailService emailService) {
        this.emailService = emailService;
    }

}

