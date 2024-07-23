package com.tokospring.common.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator") // Use UUIDGenerator strategy
    private String id; // Set default value to UUID string

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    private Boolean isDeleted;

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = null;
        this.updatedBy = null;
        this.isDeleted = false;
    }

//    private String getCurrentUser() {
//        try {
//            var email = SecurityContextHolder.getContext().getAuthentication().getName();
//            return email;
//        } catch (Exception e) {
//            log.error("Current user is SYSTEM");
//            return "SYSTEM";
//        }
//    }
}