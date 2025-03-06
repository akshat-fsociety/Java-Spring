package com.codingshuttle.SecurityApp.SecurityApplication.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String name;
    private String email;
    private String password;
}
