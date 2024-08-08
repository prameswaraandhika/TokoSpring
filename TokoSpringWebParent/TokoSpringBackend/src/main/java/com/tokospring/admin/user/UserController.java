package com.tokospring.admin.user;

import com.tokospring.admin.dto.UserDto;
import com.tokospring.common.entity.Role;
import com.tokospring.common.entity.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



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
    public String saveUser(@Valid @ModelAttribute("user") UserDto user,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            bindingResult.rejectValue("email", "error.user", "Email has been registered");
            return "user_form";
        }
        var userNew = User.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .confirmPassword(user.getConfirmPassword())
                .roles(user.getRoles())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .enabled(user.isEnabled())
                .build();
        encodePassword(userNew);
            userService.save(userNew);

            redirectAttributes.addFlashAttribute("message", "the user has been saved success");
        return "redirect:/users";
    }

    @ModelAttribute("listRoles")
    public List<Role> messages() {
        return userService.listRoles();
    }

    private void encodePassword(User user) {
        var encoded = new BCryptPasswordEncoder().encode(user.getPassword());
        var encodedConfirmPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encoded);
        user.setConfirmPassword(encodedConfirmPassword);
    }


}
