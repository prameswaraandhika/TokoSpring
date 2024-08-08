package com.tokospring.admin.user;

import com.tokospring.common.entity.Role;
import com.tokospring.common.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public List<User> listAll(){
        return (List<User>) userRepository.findAll();
    }

    public List<Role> listRoles(){
        return (List<Role>) roleRepository.findAll();
    }

    public void save(User user) {
       userRepository.save(user);
    }

    public boolean isEmailUnique(String email){
        var user = userRepository.findByEmail(email);
        return user == null;
    }
}
