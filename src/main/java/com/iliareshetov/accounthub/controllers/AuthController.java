package com.iliareshetov.accounthub.controllers;

import com.iliareshetov.accounthub.dto.UserDto;
import com.iliareshetov.accounthub.entity.User;
import com.iliareshetov.accounthub.services.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        logger.info("/login");
        return "login";
    }

    @GetMapping("register")
    public String showRegistrationForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model) {
        logger.info("/register/save");
        try {
            User existing = userService.findByEmail(user.getEmail());
            result.rejectValue("email", null, "There is already an account registered with that email");
        } catch (UsernameNotFoundException e) {

        }

        if (result.hasErrors()) {
            logger.info("result.hasErrors():{}", result.hasErrors());
            model.addAttribute("user", user);
            return "register";
        }
        logger.info("user.getFirstName():{}", user.getFirstName());
        logger.info("user.getLastName():{}", user.getLastName());
        logger.info("user.getPassword():{}", user.getPassword());
        logger.info("user.getBirthday():{}", user.getBirthday());
        userService.saveUser(user);

        return "redirect:/register?success";
    }

}
