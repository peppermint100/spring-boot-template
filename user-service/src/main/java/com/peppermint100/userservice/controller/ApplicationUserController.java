package com.peppermint100.userservice.controller;

import com.peppermint100.userservice.dto.ApplicationUserDto;
import com.peppermint100.userservice.service.ApplicationUserService;
import com.peppermint100.userservice.vo.GlobalResponse;
import com.peppermint100.userservice.vo.RegisterUserRequest;
import com.peppermint100.userservice.vo.ApplicationUserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("users")
@RestController
@RequiredArgsConstructor
public class ApplicationUserController {

    private final ApplicationUserService userService;

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
}
