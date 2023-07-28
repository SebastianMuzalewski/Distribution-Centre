package com.deez.distribution_center.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @GetMapping
    public String profile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean hasRoleAdmin = authentication != null && authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
        model.addAttribute("hasRoleAdmin", hasRoleAdmin);

        boolean hasRoleEmp = authentication != null && authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_EMPLOYEE"));
        model.addAttribute("hasRoleEmp", hasRoleEmp);

        boolean hasRoleUser = authentication != null && authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_USER"));
        model.addAttribute("hasRoleUser", hasRoleUser);

        String userRole = null;
        String username = null;
        if (authentication != null && !authentication.getAuthorities().isEmpty()) {
            userRole = authentication.getAuthorities().iterator().next().getAuthority();
            username = authentication.getName();
        }
        model.addAttribute("userRole", userRole);
        model.addAttribute("username", username);

        return "profile";
    }
}
