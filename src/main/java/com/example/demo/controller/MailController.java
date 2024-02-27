package com.example.demo.controller;

import com.example.demo.dto.MailRequest;
import com.example.demo.service.MailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mail")
@RequiredArgsConstructor
public class MailController {

  private final MailService mailService;

  @PostMapping
  public void sendMail(@RequestBody @Valid MailRequest mailRequest){
    mailService.sendSimpleMessage(mailRequest);
  }

}
