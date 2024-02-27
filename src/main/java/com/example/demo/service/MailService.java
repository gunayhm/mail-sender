package com.example.demo.service;

import com.example.demo.dto.MailRequest;
import com.example.demo.util.FileGenerator;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {

  private final JavaMailSender emailSender;
  private final FileGenerator fileGenerator;

  public void sendSimpleMessage(MailRequest mailRequest) {
    MimeMessage message = emailSender.createMimeMessage();
    MimeMessageHelper helper = null;

    try {
      helper = new MimeMessageHelper(message, true);
      helper.setTo(mailRequest.getMailTo());
      helper.setSubject(mailRequest.getSubject());
      helper.setText(mailRequest.getText());
      if (Objects.nonNull(mailRequest.getEmployeeDto())) {
        byte[] excelContent = fileGenerator.generateExcelFile(mailRequest.getEmployeeDto());
        helper.addAttachment("javarush-employee.xlsx", new ByteArrayResource(excelContent));
      }
    } catch (MessagingException e) {
      log.error("Send mail to {} completed with exception ", mailRequest.getMailTo());
      throw new RuntimeException(e);
    }

    emailSender.send(message);
    log.info("Send mail to {} successfully completed !", mailRequest.getMailTo());
  }

}