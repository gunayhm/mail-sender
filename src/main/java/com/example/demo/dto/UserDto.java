package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable{

    @NotBlank
    private String firstName;

    @NotNull
    private Integer age;

    @NotBlank
    private String phoneNumber;

}
