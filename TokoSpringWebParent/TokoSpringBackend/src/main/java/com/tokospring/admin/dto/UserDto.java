package com.tokospring.admin.dto;

import com.tokospring.admin.validators.PasswordEqualConstrain;
import com.tokospring.admin.validators.UniqueEmailConstraint;
import com.tokospring.common.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@PasswordEqualConstrain
@Getter
@Setter
public class UserDto {

    @NotNull
    private String id;

    @Email(message = "invalid input format")
    @NotEmpty(message = "email is required")
    @UniqueEmailConstraint
    private String email;

    @NotNull
    private String password;
    @NotNull
    private String confirmPassword;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    private String photos;

    @NotNull
    private boolean enabled;

    private Set<Role> roles = new HashSet<>();


}