package com.example.gatm.service;

import com.example.gatm.dto.EmailDto;
import org.springframework.web.multipart.MultipartFile;

public interface EmailService {
    void sendEmail(String email, String subject, String body);

}
