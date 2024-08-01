package com.tokospring.admin.user;

import com.tokospring.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    
    @GetMapping(value = "/users/new")
    public String createUser(Model model){
        var user = new User();
        var roles = userService.listRoles();
        model.addAttribute("user", user);
        model.addAttribute("listRoles", roles);
        return "user_form";
    }

    @PostMapping(value = "/users/save")
    public String saveUser(User user, RedirectAttributes redirectAttributes){
        userService.save(user);
        redirectAttributes.addFlashAttribute("message", "the user has been saved success");
        return "redirect:/users";
    }

    
}
