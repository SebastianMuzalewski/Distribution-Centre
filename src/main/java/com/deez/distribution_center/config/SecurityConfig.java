package com.deez.distribution_center.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.deez.distribution_center.model.User;
import com.deez.distribution_center.repository.UserRepository;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return username -> {
            User user = userRepository.findByUsername(username);
            if(user !=null){
                return user;
            }
            throw new UsernameNotFoundException(username);
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests()
                    .requestMatchers(toH2Console()).permitAll()
                    // User role with ADMIN can only access certain url pages
                    .requestMatchers("")
                    .hasRole("ADMIN")
                    // User role with EMPLOYEE can only certain url pages
                    .requestMatchers("")
                    .hasRole("EMPLOYEES")
                    // User with basic role USER can only access these url pages
                    .requestMatchers("")
                    .hasRole("USER")
                    .requestMatchers("/home", "/login", "/register", "/images/**")
                    .permitAll()
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/home", true)
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout")
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/home")
                .and()
                .exceptionHandling()
                    .accessDeniedPage("/login")
                .and()
                .headers()
                    .frameOptions()
                    .sameOrigin()
                .and()
                .csrf()
                    .disable()
                .build();
    }
}