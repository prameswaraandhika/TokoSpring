package com.tokospring.common.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Builder
public class User extends BaseEntity {

    @Column(unique=true)
    @Email
    private String email;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;
    private String photos;
    private boolean enabled;
    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id"
            )
    )
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role){
        roles.add(role);
    }

}
