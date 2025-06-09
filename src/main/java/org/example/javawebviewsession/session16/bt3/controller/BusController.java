package org.example.javawebviewsession.session16.bt3.controller;

import org.example.javawebviewsession.session16.bt3.dao.BusDao;
import org.example.javawebviewsession.session16.bt3.model.Bus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

@Controller
public class BusController {

    private final BusDao busDao = new BusDao();

    @GetMapping("/admin")
    public String showForm(Model model) {
        model.addAttribute("bus", new Bus());
        return "admin";
    }

    @PostMapping("/admin")
    public String addBus(@ModelAttribute("bus") @Valid Bus bus,
                         BindingResult result,
                         Model model,
                         @RequestParam("file") MultipartFile file) throws IOException {
        if (result.hasErrors()) {
            return "admin";
        }

        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String uploadDir = "C:\\Users\\DELL\\IdeaProjects\\JavaWebViewSession\\file\\";
            File dest = new File(uploadDir + fileName);
            file.transferTo(dest);

            bus.setImage(fileName);
            model.addAttribute("message", "Upload thành công: " + fileName);
        } else {
            model.addAttribute("message", "Vui lòng chọn file.");
            return "admin";
        }

        bus.setTotalSeat(bus.getRowSeat() * bus.getColSeat());

        busDao.insertBus(bus);
        model.addAttribute("message", "Thêm xe thành công!");
        return "redirect:/admin";
    }
}
