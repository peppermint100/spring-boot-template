package com.peppermint100.userservice.dto;

import com.peppermint100.userservice.entity.ApplicationUser;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationUserDto {

    private UUID id;
    private String email;
    private Instant createdAt;
    private Instant updatedAt;

    public static ApplicationUserDto of(ApplicationUser entity) {
        return new ApplicationUserDto(entity.getId(), entity.getEmail(), entity.getCreatedAt(), entity.getUpdatedAt());
    }
}
