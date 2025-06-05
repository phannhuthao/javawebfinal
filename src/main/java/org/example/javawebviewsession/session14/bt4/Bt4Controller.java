package org.example.javawebviewsession.session14.bt4;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;
import java.util.*;

@Controller
public class Bt4Controller {

    @GetMapping("/bt4")
    public String bt4(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();

        // Lấy giỏ hàng từ session
        List<String> cart = (List<String>) session.getAttribute("cart");
        if (cart == null) cart = new ArrayList<>();

        // Lấy thông tin sản phẩm từ cookie
        List<String> products = new ArrayList<>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().startsWith("product_")) {
                    products.add(c.getValue());
                }
            }
        }

        model.addAttribute("cart", cart);
        model.addAttribute("products", products);
        return "bt4";
    }

    @PostMapping("/bt4Show")
    public String bt4Show(@RequestParam String name,
                          @RequestParam int quantity,
                          HttpServletRequest request,
                          HttpServletResponse response) {

        HttpSession session = request.getSession();
        List<String> cart = (List<String>) session.getAttribute("cart");
        if (cart == null) cart = new ArrayList<>();

        String item = "|Tên:" + name + " |Số Lượng: " + quantity;
        cart.add(item);
        session.setAttribute("cart", cart);

        // Lưu vào cookie
        Cookie productCookie = new Cookie("product_" + UUID.randomUUID(), name + ":" + quantity);
        productCookie.setMaxAge(60 * 60 * 24);
        response.addCookie(productCookie);

        return "redirect:/bt4";
    }

    @GetMapping("/remove")
    public String removeItem(@RequestParam int index, HttpSession session) {
        List<String> cart = (List<String>) session.getAttribute("cart");
        if (cart != null && index < cart.size()) {
            cart.remove(index);
            session.setAttribute("cart", cart);
        }
        return "redirect:/bt4";
    }
}
