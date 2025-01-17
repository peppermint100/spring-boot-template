package com.peppermint100.templateservice.repository;

import com.peppermint100.templateservice.entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TemplateRepository extends JpaRepository<Template, UUID> {
}
