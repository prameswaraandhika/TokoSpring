package com.tokospring.admin.user;

import com.tokospring.common.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, String> {
}
