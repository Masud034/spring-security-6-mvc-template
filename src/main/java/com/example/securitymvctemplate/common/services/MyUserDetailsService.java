package com.example.securitymvctemplate.common.services;

import com.example.securitymvctemplate.authority.AuthorityEntity;
import com.example.securitymvctemplate.common.entities.UserEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userService.findUserByUsername(username.toLowerCase());

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