package com.example.securitymvctemplate.role;

import com.example.securitymvctemplate.authority.AuthorityEntity;
import com.example.securitymvctemplate.common.entities.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(generator = "UUID")
	private UUID id;

	@Column(nullable = false)
	private String name;

	@ManyToMany(mappedBy="roles")
	@JsonIgnore
	private Collection<UserEntity> users;

	@ManyToMany(cascade= { CascadeType.PERSIST }, fetch = FetchType.EAGER )
	@JoinTable(name="roles_authorities",
			joinColumns=@JoinColumn(name="roles_id",referencedColumnName="id"),
			inverseJoinColumns=@JoinColumn(name="authorities_id",referencedColumnName="id"))
	private List<AuthorityEntity> authorities;
}
