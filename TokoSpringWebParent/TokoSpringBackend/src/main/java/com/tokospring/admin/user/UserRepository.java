package com.tokospring.admin.user;

import com.tokospring.common.entity.Role;
import com.tokospring.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User findByEmail(String email);

    boolean existsByEmail(String email);
}
