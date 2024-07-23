package com.tokospring.admin.user;

import com.tokospring.common.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class RoleRepositoryTests {

    @Autowired
    RoleRepository roleRepository;

    @Test
    public void testCreateRole(){
        var role = Role.builder()
                .name("Admin")
                .description("Previledge to Manage Data Master")
                .build();
        var savedRole = roleRepository.save(role);
        assertThat(savedRole.getId()).isNotNull();
    }

    @Test
    public void testCreateManyRole(){

        var roleDriver = Role.builder()
                .name("Driver")
                .description("Previledge 2")
                .build();
        var roleSeller = Role.builder()
                .name("Seller")
                .description("Previledge 3")
                .build();
        var roleUser = Role.builder()
                .name("User")
                .description("Previledge 4")
                .build();

        Iterable<Role> roles = roleRepository.saveAll(List.of(
                roleDriver,
                roleSeller,
                roleUser
        ));

        assertNotNull(roles);
    }
}
