package org.example.javawebviewsession.session14.bt3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LanguageController {

    @GetMapping("/bt3")
    public String home() {
        return "bt3";
    }

    @PostMapping("/changeLanguage")
    public String changeLanguage(@RequestParam("lang") String lang, HttpServletResponse response) {
        Cookie cookie = new Cookie("lang", lang);
        cookie.setMaxAge(60 * 60);
        response.addCookie(cookie);
        return "redirect:/home?lang=" + lang;
    }
}
