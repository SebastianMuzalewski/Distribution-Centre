package com.deez.distribution_center.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavbarController {

    @GetMapping("/navbar-about")
    public String aboutPage(Model model) {
        return "about";
    }

    @GetMapping("/navbar-home")
    public String homePage(Model model) {
        return "home";
    }

    @GetMapping("/navbar-login")
    public String loginPage(Model model) {
        return "login";
    }

    @GetMapping("/navbar-register")
    public String registerPage(Model model) {
        return "register";
    }
}