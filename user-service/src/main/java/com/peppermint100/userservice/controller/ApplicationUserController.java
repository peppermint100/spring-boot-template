package com.peppermint100.userservice.controller;

import com.peppermint100.userservice.dto.ApplicationUserDto;
import com.peppermint100.userservice.service.ApplicationUserService;
import com.peppermint100.userservice.service.AuthenticationService;
import com.peppermint100.userservice.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.web.bind.annotation.*;

@RequestMapping("users")
@RestController
@RequiredArgsConstructor
public class ApplicationUserController {

    private final ApplicationUserService userService;
    private final AuthenticationService authenticationService;

    @GetMapping("/health")
    public String checkHealth() {
        return "Health Check OK";
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody RegisterUserRequest request) {
        ApplicationUserDto userDto = userService.signUp(
                request.getEmail(),
                request.getPassword(),
                request.getConfirmPassword()
        );
        ApplicationUserVO userVO = ApplicationUserVO.of(userDto);

        GlobalResponse response = GlobalResponse.<ApplicationUserVO>builder().message("회원가입에 성공했습니다.").data(userVO).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody LoginUserRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();
        authenticationService.authenticateUser(email, password);
        String bearerToken = authenticationService.generateBearerTokenWithEmail(email);
        TokenResponse data = TokenResponse.builder().token(bearerToken).build();
        GlobalResponse response = GlobalResponse.<TokenResponse>builder().message("로그인에 성공했습니다.").data(data).build();
        return ResponseEntity.ok(response);
    }
}
