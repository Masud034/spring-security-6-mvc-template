package com.example.securitymvctemplate.authority;

import com.example.securitymvctemplate.role.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="authorities")
public class AuthorityEntity implements GrantedAuthority {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String readableName;

    @ManyToMany(mappedBy="authorities")
    @JsonIgnore
    private Collection<Role> roles;

    @ManyToOne
    private AuthorityTitle authorityTitle;

    @Override
    public String getAuthority() {
        return this.getName();
    }
}
