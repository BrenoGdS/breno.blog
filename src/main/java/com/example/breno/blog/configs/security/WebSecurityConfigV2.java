package com.example.breno.blog.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // Enable method security
public class WebSecurityConfigV2 {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(Customizer.withDefaults()); // Configure httpBasic

        http
                .authorizeHttpRequests(auth -> auth

                        //.dispatcherTypeMatchers(HttpMethod.GET, "/users/**").permitAll() // Allow GET requests to /parking-spot/** for all users
                       // .antMatchers(HttpMethod.POST, "/parking-spot").hasRole("USER") // Allow POST requests to /parking-spot for users with role USER
                       // .antMatchers(HttpMethod.DELETE, "/parking-spot/**").hasRole("ADMIN") // Allow DELETE requests to /parking-spot/** for users with role ADMIN

                        //.requestMatchers(HttpMethod.GET, "/posts/**").hasAnyRole("ADMIN") // Allow GET requests to /users/** for users with role USER
                        //.requestMatchers(HttpMethod.POST, "/users/**").hasRole("ADMIN") // Allow POST requests to /users/** for users with role ADMIN
                        .anyRequest().authenticated() // Require authentication for any request
                        //.anyRequest().permitAll() // Require authentication for any request
                )
                .csrf(csrf -> csrf.disable()); // Disable CSRF
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
