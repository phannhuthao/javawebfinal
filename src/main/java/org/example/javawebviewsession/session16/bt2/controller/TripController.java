package org.example.javawebviewsession.session16.bt2.controller;

import org.example.javawebviewsession.session16.bt2.dao.CarTripDao;
import org.example.javawebviewsession.session16.bt2.model.CarTrip;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TripController {
    private final CarTripDao carTripDao = new CarTripDao();
    private final int PAGE_SIZE = 5;

    @GetMapping("/home")
    public String showHome(
            @RequestParam(value = "search", required = false, defaultValue = "") String search,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            Model model) {

        int offset = (page - 1) * PAGE_SIZE;
        List<CarTrip> trips = carTripDao.getCarTrips(search, offset, PAGE_SIZE);
        int totalTrips = carTripDao.countTotalTrips(search);
        int totalPages = (int) Math.ceil((double) totalTrips / PAGE_SIZE);

        model.addAttribute("trips", trips);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("search", search);

        return "home";
    }
}
