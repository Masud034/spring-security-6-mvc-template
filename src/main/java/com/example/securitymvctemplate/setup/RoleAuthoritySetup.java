package com.example.securitymvctemplate.setup;

import com.example.securitymvctemplate.authority.AuthorityEntity;
import com.example.securitymvctemplate.authority.AuthorityRepository;
import com.example.securitymvctemplate.authority.AuthorityTitleRepository;
import com.example.securitymvctemplate.entities.UserEntity;
import com.example.securitymvctemplate.repositories.UserEntityRepository;
import com.example.securitymvctemplate.role.Role;
import com.example.securitymvctemplate.role.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleAuthoritySetup {

    private final AuthorityRepository authorityRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final UserEntityRepository userEntityRepository;

    @EventListener
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        Role roleAdmin = createRole("ROLE_ADMIN", assignAdminAuthority());
        createRole("ROLE_USER", assignUserAuthority());

        if (!userEntityRepository.existsByUserNameIgnoreCase("admin")) {
            UserEntity adminUser = new UserEntity();
            adminUser.setUserName("admin");
            adminUser.setPassword(encoder.encode("admin"));
            adminUser.setRoles(List.of(roleAdmin));
            userEntityRepository.save(adminUser);
        }
    }

    private List<AuthorityEntity> assignAdminAuthority() {
        List<String> adminAuthorities = List.of(
                "GET_ALL_USERS");
        List<AuthorityEntity> authorityEntities = new ArrayList<>();
        adminAuthorities.stream().forEach(s -> authorityEntities.add(authorityRepository.findByName(s)));
        return authorityEntities;
    }

    private List<AuthorityEntity> assignUserAuthority() {
        List<String> adminAuthorities = List.of(
                "BROWSE_ONLY");
        List<AuthorityEntity> authorityEntities = new ArrayList<>();
        adminAuthorities.stream().forEach(s -> authorityEntities.add(authorityRepository.findByName(s)));
        return authorityEntities;
    }

    private Role createRole(String name, List<AuthorityEntity> authorities) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role();
            role.setName(name);
            role.setAuthorities(authorities);
            roleRepository.save(role);
        }
        return role;
    }
}