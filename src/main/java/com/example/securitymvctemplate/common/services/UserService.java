package com.example.securitymvctemplate.common.services;

import com.example.securitymvctemplate.common.entities.UserEntity;
import com.example.securitymvctemplate.common.model.SignupRequest;
import com.example.securitymvctemplate.common.repositories.UserEntityRepository;
import com.example.securitymvctemplate.role.Role;
import com.example.securitymvctemplate.role.RoleRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {

    private final UserEntityRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder encoder;

    public UserService(UserEntityRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    public UserEntity findUserByUsername(String username) {
        return userRepository.findByUserNameIgnoreCase(username.toLowerCase());
    }

    public UserEntity saveUser(SignupRequest request) {
        UserEntity user = new UserEntity();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUserName(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(Arrays.asList(userRole));
        return userRepository.save(user);
    }


    public boolean isValidUserPassword(UserEntity userEntity, String oldPassword) {
       return encoder.matches(oldPassword, userEntity.getPassword());
    }
}
