package com.diaz.expense_tracker.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

/*
    This class inherits behavior from the super class UsernamePasswordAuthenticationFilter.
    This is Spring Security's default filters for username and password, what will be
    doing is customizing these to our personal login preferences that being email and
    password, while spring's default filters for login are username and password

    Filtering - a component that can perform filtering tasks on requests to and responses
    from a web application. It acts at the servlet level, meaning it can intercept requests
    before they reach your servlets (or controllers in the case of Spring Boot)
*/

@Service
public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    /*
        Our class is a bouncer at a nightclub.

        obtainUsername and obtainPassword functions are protected
        because, this is the process of the bouncer checking guests ID's. However, the bouncer has a secret
        way to check for fakes. Only the bouncer knows this no one else. This is only known to them.
        When the servlet intercepts and http request,they verify with data they know only to ensure it's
        accurate or not. Thus why it's a protected functions

        attemptAuthentication function is public
        attemptAuthentication is what the bouncer got hired for. This is what everyone sees when he checks
        the guest again and actually decides whether to let them in based on their guest list. The
        Spring Security Framework expects a repose whether to login in the user or not. This method is
        public because the framework needs to know globally about the outcome of where to direct the user.

    */

    // looks for a parameter (field) email instead of default username
    @Override
    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter("email");
    }

    //slightly redundant but practice nevertheless
    @Override
    protected String obtainPassword(HttpServletRequest request) {
        return request.getParameter("password");
    }


    // authenticates the user
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String email = obtainUsername(request);
        String password = obtainPassword(request);

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(email, password);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }



}


