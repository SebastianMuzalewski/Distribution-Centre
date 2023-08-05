package com.deez.distribution_center.controller;

import com.deez.distribution_center.model.dto.DBCentersDto;
import com.deez.distribution_center.repository.DBCRepository;
import com.deez.distribution_center.repository.DBCRepositoryPaginated;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/distribution-centers")
public class DistributionCenterController {
    private static final int PAGE_SIZE = 5;
    private DBCRepository dbcRepository;

    private DBCRepositoryPaginated dbcRepositoryPaginated;

    public DistributionCenterController(DBCRepository dbcRepository,
                                        DBCRepositoryPaginated dbcRepositoryPaginated) {
        this.dbcRepository = dbcRepository;
        this.dbcRepositoryPaginated = dbcRepositoryPaginated;
    }

    @GetMapping
    public String showDBC(Model model) {
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

        return "distribution-centers";
    }

    @ModelAttribute
    public void dbCenters(Model model) {
        var dbcPage = dbcRepositoryPaginated.findAll(PageRequest.of(0, PAGE_SIZE));
        model.addAttribute("dbCenters", dbcPage.getContent());
        model.addAttribute("currentPage", dbcPage.getNumber());
        model.addAttribute("totalPages", dbcPage.getTotalPages());
    }

    @ModelAttribute
    public void dbCentersDto(Model model) {
        model.addAttribute("dbCentersDto", new DBCentersDto());
    }

    @PostMapping
    public String searchByName(@ModelAttribute DBCentersDto dbCentersDto, Model model) {
        var name = dbCentersDto.getName();

//            model.addAttribute("dbCenters", DBCRepository.DBCFindByName(name));

        return "distribution-centers";
    }

    @GetMapping("/switchPage")
    public String switchPage(Model model,
                             @RequestParam("pageToSwitch") Optional<Integer> pageToSwitch) {
        var page = pageToSwitch.orElse(0);
        var totalPages = (int) model.getAttribute("totalPages");
        if (page < 0 || page >= totalPages) {
            return "distribution-centers";
        }
        var dbcPage = dbcRepositoryPaginated.findAll(PageRequest.of(pageToSwitch.orElse(0),
                PAGE_SIZE));
        model.addAttribute("dbCenters", dbcPage.getContent());
        model.addAttribute("currentPage", dbcPage.getNumber());
        return "distribution-centers";
    }

    @PostMapping("/delete")
    public String deleteItem(@RequestParam("id") Long centerId) {
        dbcRepository.deleteById(centerId);
        return "redirect:/distribution-centers";
    }

    @GetMapping("/details/{id}")
    public String showCenterDetails(@PathVariable("id") Long centerId, Model model) {
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
        var center = dbcRepository.findById(centerId);
        center.ifPresent(c -> model.addAttribute("center", c));
        return "center-details";
    }
}
