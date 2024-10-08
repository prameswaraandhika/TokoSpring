package com.tokospring.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "roles")
public class Role extends BaseEntity{
    @Column(nullable = false, unique = true)
    private String name;
    private String description;

    @Override
    public String toString() {
        return "Role "+ name;
    }
}
