package com.deez.distribution_center.controller;

import com.deez.distribution_center.model.dto.DBCentersDto;
import com.deez.distribution_center.repository.DBCRepository;
import com.deez.distribution_center.repository.DBCRepositoryPaginated;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/distribution-centers")
public class DistributionCenterController {
    private static final int PAGE_SIZE = 4;
    private DBCRepository dbcRepository;

    private DBCRepositoryPaginated dbcRepositoryPaginated;

    public DistributionCenterController(DBCRepository dbcRepository,
                                        DBCRepositoryPaginated dbcRepositoryPaginated) {
        this.dbcRepository = dbcRepository;
        this.dbcRepositoryPaginated = dbcRepositoryPaginated;
    }

    @GetMapping
    public String showDBC(Model model) {
        return "distribution-centers";
    }

    @ModelAttribute
    public void dbCenters(Model model) {
        var dbcPage = dbcRepositoryPaginated.findAll(PageRequest.of(0, PAGE_SIZE));
        model.addAttribute("fighters", dbcPage.getContent());
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

            model.addAttribute("dbCenters", dbcRepository.findByName(name));

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
}
