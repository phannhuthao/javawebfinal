package org.example.javawebviewsession.session15.bt1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Bt1Thymeleaf {
    @GetMapping("/ss15bt1")
        public String bt1() {
        return "bt1";
        }
}
