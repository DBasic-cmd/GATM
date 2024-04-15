package com.example.gatm.serviceImpl;

import com.example.gatm.dto.EmailDto;
import com.example.gatm.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.MimeMessage;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Value("{spring.mail.username}")
    private String fromEmail;

    private final JavaMailSender javaMailSender;
    @Override
    public String sendMail(EmailDto emailDto) {
        try {

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(fromEmail);
            mimeMessageHelper.setTo(emailDto.getTo());
            if (!Objects.isNull(emailDto.getCc()))
                mimeMessageHelper.setCc(emailDto.getCc());
            mimeMessageHelper.setSubject(emailDto.getSubject());
            mimeMessageHelper.setText(emailDto.getBody());
            if (Objects.nonNull(emailDto.getFile())) {

                for (MultipartFile multipartFile : emailDto.getFile()) {
                    mimeMessageHelper.addAttachment(
                            Objects.requireNonNull(multipartFile.getOriginalFilename()),
                            new ByteArrayResource(multipartFile.getBytes())
                    );
                }
            }
            javaMailSender.send(mimeMessage);

            return "mail send";

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
