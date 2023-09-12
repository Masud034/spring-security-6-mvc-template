package com.example.securitymvctemplate.role;

import com.example.securitymvctemplate.authority.AuthorityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    private final AuthorityRepository authorityRepository;


    @Transactional
    public void addRole(RoleAuthoritiesRequestModel requestModel) {
        List<UUID> authIds = requestModel.getAuthorityIds().stream()
                .map(UUID::fromString)
                .collect(Collectors.toList());

        Role role = new Role();
        role.setName("ROLE_"+requestModel.getName().toUpperCase());
        role.setAuthorities(authorityRepository.findAllById(authIds));
        roleRepository.save(role);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRole(UUID roleId) {
        return roleRepository.findById(roleId).get();
    }

    @Transactional
    public void editRole(Role role, String roleId) {
        Role savedRole = roleRepository.findById(UUID.fromString(roleId)).get();
        if (role.getName().startsWith("ROLE_"))
            savedRole.setName(role.getName().toUpperCase());
        else
            savedRole.setName("ROLE_"+role.getName().toUpperCase());

        savedRole.setAuthorities(role.getAuthorities());
        roleRepository.save(savedRole);
    }
}
