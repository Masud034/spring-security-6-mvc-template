package com.example.securitymvctemplate.common.repositories;

import com.example.securitymvctemplate.common.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, UUID> {
	
	Boolean existsByUserNameIgnoreCase(String username);
	
	UserEntity findByUserNameIgnoreCase(String username);
}
