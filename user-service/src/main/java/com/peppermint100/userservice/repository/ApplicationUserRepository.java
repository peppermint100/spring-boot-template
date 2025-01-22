package com.peppermint100.userservice.repository;

import com.peppermint100.userservice.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, UUID> {
    Boolean existsByEmail(String email);
    Optional<ApplicationUser> findByEmail(String email);
}
