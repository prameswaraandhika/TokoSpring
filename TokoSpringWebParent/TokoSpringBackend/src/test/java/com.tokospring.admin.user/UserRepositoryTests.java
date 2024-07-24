package com.tokospring.admin.user;

import com.tokospring.common.entity.Role;
import com.tokospring.common.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {
    private UserRepository userRepository;
    private TestEntityManager testEntityManager;

    @Autowired
    public UserRepositoryTests(UserRepository userRepository, TestEntityManager testEntityManager) {
        this.userRepository = userRepository;
        this.testEntityManager = testEntityManager;
    }

    @Test
    public void testCreateUser(){
        var roleId = "3770f539-9369-4670-8f2f-caa2c351344d";
        var roleAdmin = testEntityManager.find(Role.class, roleId);
        var secondUser = new User();
        secondUser.setEmail("seconduser@gmail.com");
        secondUser.setFirstName("Second");
        secondUser.setLastName("User Admin");
        secondUser.setPassword("password1");
        secondUser.addRole(roleAdmin);
        User savedUser = userRepository.save(secondUser);
        assertThat(savedUser.getId()).isNotNull();

    }
}
