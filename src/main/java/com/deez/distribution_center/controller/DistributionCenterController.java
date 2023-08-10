package com.deez.distribution_center.controller;

import com.deez.distribution_center.model.DistributionCenter;
import com.deez.distribution_center.model.Item;
import com.deez.distribution_center.model.dto.DBCentersDto;
import com.deez.distribution_center.repository.DBCRepository;
import com.deez.distribution_center.repository.DBCRepositoryPaginated;
import com.deez.distribution_center.repository.ItemRepository;
import com.deez.distribution_center.repository.ItemRepositoryPaginated;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/distribution-centers")
public class DistributionCenterController {
    private static final int PAGE_SIZE = 5;
    private DBCRepository dbcRepository;
    private DBCRepositoryPaginated dbcRepositoryPaginated;
    private ItemRepository itemRepository;
    private ItemRepositoryPaginated itemRepositoryPaginated;
    private double wareHouseLatitude = 43.590709;
    private double wareHouseLongitude = -79.642140;
    private double earthRadius = 6371;

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

    @PostMapping("/delete-item")
    public String deleteItem(@RequestParam("centerId") Long centerId, @RequestParam("itemId") Long itemId) {
        // Get the distribution center from the database
        Optional<DistributionCenter> centerOptional = dbcRepository.findById(centerId);

        if (centerOptional.isPresent()) {
            DistributionCenter center = centerOptional.get();
            // Get the list of items available in the distribution center
            List<Item> itemsAvailable = center.getItemsAvailable();

            // Find the item with the given ID in the list and remove it
            itemsAvailable.removeIf(item -> item.getId().equals(itemId));

            // Save the updated distribution center
            dbcRepository.save(center);
        }

        return "redirect:/distribution-centers/details/" + centerId;
    }

    @GetMapping("/add-item")
    public String showAddItemForm(Model model) {
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
        var dbCenters = dbcRepository.findAll();
        model.addAttribute("dbCenters", dbCenters);
        return "add-item";
    }

    @PostMapping("/add-item")
    public String addItem(@ModelAttribute Item newItem, @RequestParam("center") Long centerId) {
        Optional<DistributionCenter> centerOptional = dbcRepository.findById(centerId);

        if (centerOptional.isPresent()) {
            DistributionCenter center = centerOptional.get();
            List<Item> itemsAvailable = center.getItemsAvailable();
            itemsAvailable.add(newItem);
            center.setItemsAvailable(itemsAvailable);
            dbcRepository.save(center);
        }

        return "redirect:/distribution-centers";
    }


    @GetMapping("/search-item")
    public String showSearchItemForm(Model model) {
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

        List<DistributionCenter> centersWithMatchingItems = new ArrayList<>();
        model.addAttribute("centersWithMatchingItems", centersWithMatchingItems);

        var dbCenters = dbcRepository.findAll();
        model.addAttribute("dbCenters", dbCenters);
        return "search-item";
    }

    @PostMapping("/search-item")
    public String searchItem(@RequestParam("name") String name, @RequestParam("brandFrom") Item.Brand brand, Model model) {
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

        List<DistributionCenter> centersWithMatchingItems = new ArrayList<>();

        var dbCenters = dbcRepository.findAll();

        for (DistributionCenter center : dbCenters) {
            boolean hasMatchingItems = false;

            // Loop through the items in the center and check for matches
            for (Item item : center.getItemsAvailable()) {
                if (item.getName().equalsIgnoreCase(name) && item.getBrandFrom() == brand) {
                    hasMatchingItems = true;
                    break;
                }
            }

            if (hasMatchingItems) {
                double latDistance = Math.toRadians(center.getLatitude() - wareHouseLatitude);
                double lonDistance = Math.toRadians(center.getLongitude() - wareHouseLongitude);

                double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                        + Math.cos(Math.toRadians(wareHouseLatitude)) * Math.cos(Math.toRadians(center.getLatitude()))
                        * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

                double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
                double distance = earthRadius * c;

                // Round the distance to two decimal places
                distance = Math.round(distance * 100.0) / 100.0;

                center.setDistance(distance);
                centersWithMatchingItems.add(center);
            }
        }

        // Sort the centers by distance before adding them to the model
        centersWithMatchingItems.sort(Comparator.comparing(DistributionCenter::getDistance));

        model.addAttribute("centersWithMatchingItems", centersWithMatchingItems);
        return "search-item";
    }
}