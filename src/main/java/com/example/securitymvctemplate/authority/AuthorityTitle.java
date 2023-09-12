package com.example.securitymvctemplate.authority;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class AuthorityTitle {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String title;

    @OneToMany(fetch = FetchType.EAGER)
    private List<AuthorityEntity> authorities;
}
