package com.diaz.expense_tracker.security;

import com.diaz.expense_tracker.Repository.UserRepository;
import com.diaz.expense_tracker.model.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.Collections;


/*
    Let's use an analogy for this class
    We have a library system where to borrow books users have a library card.

    UserDetails(interface) is the library card

    CustomUserDetails(class) is you the librarian. Responsible for verifying library members based on their cards

    loadByUsername - the process of looking up a members details in your records to verify identity.
    In this case we look them up via email
    return statement - this is like confirming the members details and allowing them to access library's
    resources based on their membership level

*/


public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // verifies users role and acknowledges them so they can login
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with email: " + email));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }

}
