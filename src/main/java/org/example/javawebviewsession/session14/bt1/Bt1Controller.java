package org.example.javawebviewsession.session14.bt1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class Bt1Controller {

    @GetMapping("/bt1")
    public String showLoginForm(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            model.addAttribute("name", username);
            return "bt1Welcome";
        }
        return "bt1";
    }

    @PostMapping("/bt1")
    public String handleLogin(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model
    ) {
        if ("admin".equals(username) && "123".equals(password)) {
            session.setAttribute("username", username);
            return "redirect:/bt1";
        } else {
            model.addAttribute("error", "Tên người dùng hoặc mật khẩu không chính xác.");
            return "bt1";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/bt1";
    }
}
