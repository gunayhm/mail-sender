package com.example.demo.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class MailRequest {

  @NotBlank
  private String mailTo;

  @NotBlank
  private String subject;

  @NotBlank
  private String text;

  private EmployeeDto employeeDto;

}
