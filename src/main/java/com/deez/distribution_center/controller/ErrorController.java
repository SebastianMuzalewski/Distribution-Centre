package com.deez.distribution_center.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/error")
    public String showErrorPage(Model model) {
        return "error-page";
    }

    @GetMapping("/error-page-cannot-order")  // Add this mapping
    public String showCustomErrorPage(Model model) {
        model.addAttribute("errorMessage", "Item quantity is not sufficient.");
        return "error-page";
    }
}

