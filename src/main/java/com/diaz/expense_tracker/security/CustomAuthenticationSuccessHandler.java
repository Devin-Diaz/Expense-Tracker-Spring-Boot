package com.diaz.expense_tracker.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // Custom logic here. For example, redirecting to a specific URL based on the role
        // You can also use authentication object to get more details about the current user

        // For demonstration, let's redirect all users to a "/dashboard" page
        setDefaultTargetUrl("/db");

        super.onAuthenticationSuccess(request, response, authentication);
    }



}
