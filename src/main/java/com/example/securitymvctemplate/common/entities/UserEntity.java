package com.example.securitymvctemplate.common.entities;


import com.example.securitymvctemplate.role.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserEntity {
	@Id
	@GeneratedValue(generator = "UUID")
	private UUID id;

	private String firstName;

	private String lastName;

	@Column(unique = true)
	private String userName;

	@Column(name = "password")
	private String password;

	@ManyToMany(cascade= { CascadeType.MERGE }, fetch = FetchType.EAGER )
	@JoinTable(name="users_roles",
			joinColumns=@JoinColumn(name="users_id",referencedColumnName="id"))
	private List<Role> roles;

	private boolean status = true;
}
