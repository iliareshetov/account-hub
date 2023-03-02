package com.iliareshetov.accounthub.controllers;

import com.iliareshetov.accounthub.entity.User;
import com.iliareshetov.accounthub.services.UserService;
import com.iliareshetov.accounthub.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String getUserDetails(Authentication authentication, Model model) {
        String email = authentication.getName();
        logger.info("email:{}", email);
        User user = userService.findByEmail(email);
        model.addAttribute("user", user);
        return "user";
    }

    @PostMapping("/update-user")
    public String updateUser(@ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        logger.info("/update-user");
        UserValidator validator = new UserValidator();
        validator.validate(user, bindingResult);

        logger.info("getEmail:{}", user.getEmail());

        if (bindingResult.hasErrors()) {
            return "user-edit";
        } else {
            try {
                userService.updateUser(user);
                model.addAttribute("successMessage", "User information updated successfully.");
                return "user-details";
            } catch (UsernameNotFoundException e) {
                bindingResult.rejectValue("id", "error.user", "User not found.");
                return "user-edit";
            }
        }
    }

}
