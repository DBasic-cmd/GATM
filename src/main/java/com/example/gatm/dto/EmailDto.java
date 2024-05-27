package com.example.gatm.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class EmailDto {
   private MultipartFile[] file;
    private String to;
    private String[] cc;
    private String subject;
    private String body;
}