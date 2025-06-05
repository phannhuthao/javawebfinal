package org.example.javawebviewsession.session14.bt5.controller;

import org.example.javawebviewsession.session14.bt5.model.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Bt5Controller {
    @GetMapping("/bt5")
    public String showOrders(HttpServletRequest request, Model model,
                             @RequestParam(value = "editId", required = false) String editId) {
        HttpSession session = request.getSession();
        List<Order> orders = (List<Order>) session.getAttribute("orders");
        if (orders == null) {
            orders = new ArrayList<>();
        }
        model.addAttribute("orders", orders);

        Order editOrder = null;
        if (editId != null) {
            for (Order o : orders) {
                if (o.getId().equals(editId)) {
                    editOrder = o;
                    break;
                }
            }
        }
        model.addAttribute("editOrder", editOrder);

        return "bt5";
    }

    @PostMapping("/bt5/addOrUpdate")
    public String addOrUpdateOrder(HttpServletRequest request,
                                   @RequestParam String id,
                                   @RequestParam String name,
                                   @RequestParam int quantity) {
        HttpSession session = request.getSession();
        List<Order> orders = (List<Order>) session.getAttribute("orders");
        if (orders == null) {
            orders = new ArrayList<>();
        }
        // Kiểm tra nếu id tồn tại thì update, ko thì thêm mới
        boolean updated = false;
        for (Order o : orders) {
            if (o.getId().equals(id)) {
                o.setName(name);
                o.setQuantity(quantity);
                updated = true;
                break;
            }
        }
        if (!updated) {
            orders.add(new Order(id, name, quantity));
        }
        session.setAttribute("orders", orders);
        return "redirect:/bt5";
    }

    @GetMapping("/bt5/delete")
    public String deleteOrder(HttpServletRequest request, @RequestParam String id) {
        HttpSession session = request.getSession();
        List<Order> orders = (List<Order>) session.getAttribute("orders");
        if (orders != null) {
            orders.removeIf(o -> o.getId().equals(id));
            session.setAttribute("orders", orders);
        }
        return "redirect:/bt5";
    }
}
