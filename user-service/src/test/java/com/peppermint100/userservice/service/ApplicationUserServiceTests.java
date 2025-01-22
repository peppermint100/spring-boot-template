package com.peppermint100.userservice.service;

import com.peppermint100.userservice.dto.ApplicationUserDto;
import com.peppermint100.userservice.entity.ApplicationUser;
import com.peppermint100.userservice.exception.FailToSignUpException;
import com.peppermint100.userservice.repository.ApplicationUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class ApplicationUserServiceTests {

    @InjectMocks
    private ApplicationUserService applicationUserService;

    @Mock
    private ApplicationUserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    private String email;
    private String password;
    private String invalidConfirmPassword;
    private String confirmPassword;
    private String encodedPassword;

    @BeforeEach
    void setUp() {
        email = "test@example.com";
        password = "password";
        invalidConfirmPassword = "invalidPassword";
        confirmPassword = "password";
        encodedPassword = "encodedPassword";
    }

    @Test
    void 정상적인_회원가입() {
        ApplicationUser userEntity = new ApplicationUser(UUID.randomUUID(), email, password, Instant.now(), Instant.now());
        given(userRepository.existsByEmail(email)).willReturn(false);
        given(passwordEncoder.encode(password)).willReturn(encodedPassword);
        given(userRepository.saveAndFlush(any(ApplicationUser.class))).willReturn(userEntity);

        ApplicationUserDto userDto = applicationUserService.signUp(email, password, confirmPassword);

        assertEquals(email, userDto.getEmail());
        verify(userRepository).existsByEmail(email);
        verify(passwordEncoder).encode(password);
        verify(userRepository).saveAndFlush(any(ApplicationUser.class));
    }

    @Test
    void 비밀번호_비밀번호확인_불일치시_예외() {
        assertThrows(FailToSignUpException.class, () -> applicationUserService.signUp(email, password, invalidConfirmPassword));
    }

    @Test
    void 이미_존재하는_이메일로_회원가입_시도시_예외() {
        given(userRepository.existsByEmail(email)).willReturn(true);

        assertThrows(FailToSignUpException.class, () -> applicationUserService.signUp(email, password, confirmPassword));
        verify(userRepository).existsByEmail(email);
    }
}
