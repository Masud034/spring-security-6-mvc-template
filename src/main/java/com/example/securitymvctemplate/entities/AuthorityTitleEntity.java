package com.example.securitymvctemplate.entities;

import com.example.securitymvctemplate.authority.AuthorityEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class AuthorityTitleEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String title;

    @OneToMany(fetch = FetchType.EAGER)
    private List<AuthorityEntity> authorities;
}