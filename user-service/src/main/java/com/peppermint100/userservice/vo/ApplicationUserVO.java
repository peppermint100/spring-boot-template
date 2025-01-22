package com.peppermint100.userservice.vo;

import com.peppermint100.userservice.dto.ApplicationUserDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationUserVO {
    private UUID id;
    private String email;
    private Instant createdAt;
    private Instant updatedAt;

    public static ApplicationUserVO of(ApplicationUserDto dto) {
        return new ApplicationUserVO(dto.getId(), dto.getEmail(), dto.getCreatedAt(), dto.getUpdatedAt());
    }
}
