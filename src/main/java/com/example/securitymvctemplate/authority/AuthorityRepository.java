package com.example.securitymvctemplate.authority;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AuthorityRepository extends JpaRepository<AuthorityEntity, UUID> {
    AuthorityEntity findByName(String name);

    List<AuthorityEntity> findByNameContaining(String lead);

}
