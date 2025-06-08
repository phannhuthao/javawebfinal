package org.example.javawebviewsession.session15.bt5.controller;

import org.example.javawebviewsession.session15.bt5.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class Bt5S15Controller {

    private final List<Product> productList = new ArrayList<>();

    public Bt5S15Controller() {
        productList.add(new Product("P01", "Laptop", 15000000));
        productList.add(new Product("P02", "Phone", 8000000));
        productList.add(new Product("P03", "Tablet", 6000000));
    }

    @GetMapping("/ss15bt5")
    public String showProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("results", new ArrayList<Product>());
        return "bt5";
    }

    @PostMapping("/ss15bt5")
    public String searchProduct(@ModelAttribute("product") Product product, Model model) {
        String keyword = product.getName();

        List<Product> results = new ArrayList<>();
        if (keyword != null && !keyword.trim().isEmpty()) {
            String lowerKeyword = keyword.trim().toLowerCase();
            results = productList.stream()
                    // dùng filler để lọc
                    //  nếu mã sản phẩm (id) hoặc tên sản phẩm (name) chứa từ khóa (không phân biệt hoa thường).
                    .filter(p -> p.getId().toLowerCase().contains(lowerKeyword) ||
                            p.getName().toLowerCase().contains(lowerKeyword))
                    // sau đó gom các sản phẩm phù hợp vào danh sách results
                    .collect(Collectors.toList());
        }

        model.addAttribute("results", results);
        model.addAttribute("product", product);
        model.addAttribute("error", keyword == null || keyword.trim().isEmpty());
        return "bt5";
    }
}
