package com.example.securitymvctemplate.authority;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    public List<AuthorityEntity> getAllAuthorities() {
        return authorityRepository.findAll();
    }
}
