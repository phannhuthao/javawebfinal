package org.example.javawebviewsession.session14.bt2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Bt2Controller {
    @GetMapping("/bt2")
    public String showForm(HttpServletRequest request, Model model) {
        List<String> products = new ArrayList<>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().startsWith("product_")) {
                    products.add(URLDecoder.decode(cookie.getValue()));
                }
            }
        }
        model.addAttribute("products", products);
        return "bt2";
    }

    // Thêm sản phẩm vào cookie
    @PostMapping("/bt2")
    public String addProduct(@RequestParam String id,
                             @RequestParam String name,
                             @RequestParam int price,
                             HttpServletResponse response) {
        String product = id + " - " + name + " - " + price;
        String cookieName = "product_" + id;
        Cookie cookie = new Cookie(cookieName, URLEncoder.encode(product));
        cookie.setMaxAge(60 * 60); // 1 giờ
        response.addCookie(cookie);
        return "redirect:/bt2";
    }

    // Xóa sản phẩm
    @GetMapping("/bt2/delete")
    public String deleteProduct(@RequestParam String id, HttpServletResponse response) {
        Cookie cookie = new Cookie("product_" + id, null);
        cookie.setMaxAge(0); // Xóa cookie
        response.addCookie(cookie);
        return "redirect:/bt2";
    }
}
