package com.example.securitymvctemplate.common.services;

import com.example.securitymvctemplate.authority.AuthorityEntity;
import com.example.securitymvctemplate.common.entities.UserEntity;
import com.example.securitymvctemplate.common.repositories.UserEntityRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserEntityRepository userEntityRepository;

    public MyUserDetailsService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userEntityRepository.findByUserNameIgnoreCase(username);

        if (userEntity == null || !userEntity.isStatus()) {
            throw new UsernameNotFoundException("User Not Found or Inactive");
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (AuthorityEntity authority : userEntity.getRoles().get(0).getAuthorities()) {
            authorities.add(new SimpleGrantedAuthority(authority.getName()));
        }
        return new User(userEntity.getUserName(), userEntity.getPassword(), authorities);
    }
}