package com.example.connexion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecuritySetup {

    @Bean
    public UserDetailsService accountManager() {
        UserDetails manager = User.withUsername("manager")
                .password("{noop}admin999")
                .roles("MANAGER")
                .build();

        UserDetails employee = User.withUsername("employee")
                .password("{noop}emp888")
                .roles("EMPLOYEE")
                .build();

        return new InMemoryUserDetailsManager(manager, employee);
    }

    @Bean
    public SecurityFilterChain filterChainConfiguration(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/signin", "/assets/**").permitAll()
                        .requestMatchers("/manager/**").hasRole("MANAGER")
                        .requestMatchers("/employee/**").hasAnyRole("EMPLOYEE", "MANAGER")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/signin")
                        .loginProcessingUrl("/process-signin")
                        .defaultSuccessUrl("/welcome", true)
                        .failureUrl("/signin?invalid=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/log-off")
                        .logoutSuccessUrl("/signin?loggedout=true")
                        .permitAll()
                );

        return http.build();
    }
}