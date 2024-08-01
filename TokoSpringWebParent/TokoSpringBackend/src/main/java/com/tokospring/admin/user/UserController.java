package com.tokospring.admin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public String listAll(Model model){
        var listUsers = userService.listAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }


    
}
