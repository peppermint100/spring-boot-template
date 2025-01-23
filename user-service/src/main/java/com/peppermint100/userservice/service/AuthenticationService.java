package com.peppermint100.userservice.service;

import com.peppermint100.userservice.exception.FailToLoginException;
import com.peppermint100.userservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtUtil jwtUtil;
    private final AuthenticationProvider authenticationProvider;

    public void authenticateUser(String email, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password, List.of());
        try {
            authenticationProvider.authenticate(authenticationToken);
        } catch (AuthenticationException ex) {
            throw new FailToLoginException("비밀번호가 일치하지 않습니다.");
        }
    }
    public String generateBearerTokenWithEmail(String email) {
        return jwtUtil.createBearerTokenWithEmail(email);
    }
}
