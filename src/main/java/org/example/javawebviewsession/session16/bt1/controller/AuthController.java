package org.example.javawebviewsession.session16.bt1.controller;

import org.example.javawebviewsession.session16.bt1.dao.UserDao;
import org.example.javawebviewsession.session16.bt1.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AuthController {

    private final UserDao userService = new UserDao();

    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        user.setStatus(true);
        user.setRole("USER");
        boolean saved = userService.save(user);

        if (!saved) {
            model.addAttribute("error", "Tên đăng nhập đã tồn tại");
            return "register";
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLogin(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("user") User user,
                        BindingResult bindingResult,
                        Model model) {
        if (bindingResult.hasErrors()) {
            return "login";
        }

        User found = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());

        if (found == null) {
            model.addAttribute("error", "Sai tên đăng nhập hoặc mật khẩu");
            return "login";
        }

        if (found.getRole().equals("ADMIN")) {
            return "admin";
        } else {
            return "home";
        }
    }

}
