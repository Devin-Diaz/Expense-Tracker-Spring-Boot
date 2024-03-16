package com.diaz.expense_tracker.controller;

import com.diaz.expense_tracker.dto.UserDto;
import com.diaz.expense_tracker.model.User;
import com.diaz.expense_tracker.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }


    //getting register page data
    @GetMapping("/register")
    public String displayRegistrationForm() {
        return "register";
    }

    //getting login page data
    @GetMapping("/login")
    public String displayLoginForm() {
        return "login";
    }

    //requesting to save data to database upon registering
    //@ModelAttribute tells Spring to create an instance of User and populate its fields w/ form inputs in thymeleaf
    //@RedirectAttributes - "I'm going to send the user to another page but I want them to see this message when
    //they get there. Please carry this message on to the next page.
    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserDto userDto, RedirectAttributes redirectAttributes) {
        userService.saveUser(userDto);
        redirectAttributes.addFlashAttribute("successMessage", "Registration Successful!");
        return "redirect:login";
    }

}
