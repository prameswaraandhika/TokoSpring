package com.tokospring.common.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String email;
    private String password;
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
