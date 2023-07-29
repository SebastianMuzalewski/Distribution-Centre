package com.deez.distribution_center.controller;

import com.deez.distribution_center.model.User;
import com.deez.distribution_center.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserRepository userRepository;

    @Autowired
    public ProfileController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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

        String imageUrl = null;
        if (authentication != null) {
            User user = userRepository.findByUsername(authentication.getName());
            if (user != null && user.getImageUrl() != null) {
                imageUrl = user.getImageUrl();
            }
        }

        model.addAttribute("userRole", userRole);
        model.addAttribute("username", username);
        model.addAttribute("imageUrl", imageUrl);

//        System.out.println("User Image URL: " + imageUrl); // Debugging

        return "profile";
    }
}
