package com.diaz.expense_tracker.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/* CSRF - Cross-Site-Request-Forgery. type of security vulnerability that tricks a web browser
into executing unwanted actions in a web application where a user is authenticated for transactions,
A defense is CSRF tokens. This means that attackers cannot guess or forge requests as this token,
is constantly changing. This ensures that the requests are intentional and authorized by the user,
thus protecting against CSRF vulnerabilities. */


/*
    What is this class for?

    Who can enter without a pass (.permitAll() for /register and /login).
    Who needs to be checked before entering other areas (.anyRequest().authenticated()).
    How members can enter the club (using .formLogin() with a custom login page).
    How members leave (using .logout()).
*/


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }


    // filterChain method sets up the specific security rules for entering the club:
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(authorization -> authorization
                        .requestMatchers("/register", "/login").permitAll()
                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard", true)
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutSuccessUrl("/login")

                );

        return http.build();
    }



    /* The passwordEncoder is like a machine that encrypts membership cards.
    When someone signs up or changes their membership details, their card gets encrypted by this machine
    (BCryptPasswordEncoder), making it hard for imposters to fake membership or steal cards */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /*  a special bouncer equipped with a unique list that allows entry based on more than just a name —
    perhaps a special membership number or a code (email in your case). This bouncer stands before the main bouncer,
    checking these unique codes using a special system (AuthenticationManager) to decide if someone can proceed to the
    sign-in sheet or main hall. */
    @Bean
    public CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) throws Exception {
        CustomUsernamePasswordAuthenticationFilter filter = new CustomUsernamePasswordAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManager);
        // Configure other properties on the filter as necessary
        return filter;
    }


}
