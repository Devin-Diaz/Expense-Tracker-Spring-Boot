package com.diaz.expense_tracker.controller;

import com.diaz.expense_tracker.Repository.UserRepository;
import com.diaz.expense_tracker.model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class ExpenseController {

    private final UserRepository userRepository;

    public ExpenseController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @GetMapping("/dashboard")
    public String showDashboard(Model model, Principal principal) {
        // Optional: Use the principal to fetch user-specific data
        String email = principal.getName(); // This gets the username (email in your case)
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Load data needed for the dashboard. For example, user's tasks, profile info, etc.
        // model.addAttribute("tasks", tasksService.getTasksForUser(user.getId()));

        return "dashboard"; // Return the view for the dashboard
    }




}
