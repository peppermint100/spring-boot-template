package com.peppermint100.userservice.vo;

import lombok.Data;

@Data
public class LoginUserRequest {
    private String email;
    private String password;
}
