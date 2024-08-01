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

    @Test
    public void testCreateUserWithTwoRoles(){
        var driverId = "183cae3f-1702-4e32-84af-318cea6cdfa6";
        var roleDriver = testEntityManager.find(Role.class, driverId);
        var sellerId = "f7e39d5d-e5be-4ad1-8469-fd2179e228fa";
        var roleSeller = testEntityManager.find(Role.class, sellerId);
        var thirdUser = new User();
        thirdUser.setEmail("thirduser@gmail.com");
        thirdUser.setFirstName("third");
        thirdUser.setLastName("User Driver");
        thirdUser.setPassword("password1");
        thirdUser.addRole(roleDriver);
        thirdUser.addRole(roleSeller);
        User savedUser = userRepository.save(thirdUser);
        assertThat(savedUser.getId()).isNotNull();
    }

    @Test
    public void testListAllUsers(){
        var listUsers = userRepository.findAll();
        listUsers.forEach(System.out::println);
    }

    @Test
    public void testGetUserById(){
        var user = userRepository.findById("582b7d89-4932-4830-aa12-5e56ebe17673");
        System.out.println(user);
        assertThat(user).isNotNull();
    }

    @Test
    public void testUpdateEnableUser(){
        var user = userRepository.findById("582b7d89-4932-4830-aa12-5e56ebe17673");
        if (user.isPresent()){
            user.get().setEnabled(true);
            userRepository.save(user.get());
        }
    }

    @Test
    public void testUpdateUserRoles(){
        var driverId = "183cae3f-1702-4e32-84af-318cea6cdfa6";
        var roleDriver = testEntityManager.find(Role.class, driverId);
        var user = userRepository.findById("582b7d89-4932-4830-aa12-5e56ebe17673").get();
        user.getRoles().remove(roleDriver);
        userRepository.save(user);
    }

    @Test
    public void testDeleteUser(){
        var user = userRepository.findById("582b7d89-4932-4830-aa12-5e56ebe17673");
        user.ifPresent(value -> userRepository.delete(value));
    }
}
