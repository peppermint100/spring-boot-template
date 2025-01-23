package com.peppermint100.userservice.vo;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String email;
    private String password;
    private String confirmPassword;
}
