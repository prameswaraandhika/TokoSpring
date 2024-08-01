package com.tokospring.admin.user;

import com.tokospring.common.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> listAll(){
        return (List<User>) userRepository.findAll();
    }

}
