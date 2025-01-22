package com.peppermint100.userservice.service;

import com.peppermint100.userservice.dto.ApplicationUserDto;
import com.peppermint100.userservice.entity.ApplicationUser;
import com.peppermint100.userservice.exception.FailToSignUpException;
import com.peppermint100.userservice.repository.ApplicationUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplicationUserService {

    private final ApplicationUserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public ApplicationUserDto signUp(String email, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new FailToSignUpException("비밀번호와 비밀번호 확인이 일치하지 않습니다");
        }

        Boolean userExistsByEmail = userRepository.existsByEmail(email);
        if (userExistsByEmail) {
            throw new FailToSignUpException("중복된 이메일입니다.");
        }

        // TODO: EmailService를 통한 이메일 인증 레이어 추가

        String encryptedPassword = passwordEncoder.encode(password);

        ApplicationUser user = ApplicationUser.of(email, encryptedPassword);
        ApplicationUser userEntity = userRepository.saveAndFlush(user);

        return ApplicationUserDto.of(userEntity);
    }
}
