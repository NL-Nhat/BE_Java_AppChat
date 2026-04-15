package com.example.chatMulticat.dto.request;

import lombok.Data;

@Data
public class RegisterRequest {

    private String userName;
    private String pass;
    private String confirmPassword;

}
