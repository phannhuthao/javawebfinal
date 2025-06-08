package org.example.javawebviewsession.session15.bt3.controller;

import org.example.javawebviewsession.session15.bt3.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class Bt3Controller {

    @GetMapping("/ss15bt3")
    public String showRegister(Model model) {
        model.addAttribute("user", new User());
        return "bt3";
    }

    @PostMapping("/ss15Bt3Result")
    public String showResult(@Valid @ModelAttribute("user") User user,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            return "bt3";
        }
        model.addAttribute("user", user);
        return "bt3Result";
    }
}
