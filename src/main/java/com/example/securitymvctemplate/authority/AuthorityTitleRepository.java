package com.example.securitymvctemplate.authority;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorityTitleRepository extends JpaRepository<AuthorityTitle, UUID> {
    boolean existsByTitle(String lead);

    AuthorityTitle findByTitle(String smsLogs);
}
