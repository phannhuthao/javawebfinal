package org.example.javawebviewsession.session15.bt2.controller;

import org.example.javawebviewsession.session15.bt2.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Bt2Thymeleaf {
    @GetMapping("/ss15bt2")
    public String showStudent(Model model){
        List<Student> students = new ArrayList<>();
        students.add(new Student("S001", "Phan Nhựt Hào", 20, "12A3", "hao@gmail.com", "Đồng Nai", "0123456789"));
        students.add(new Student("S002", "Nguyễn Thanh Hạ", 20, "12A1", "ha@gmail.com", "Đồng Nai", "0987654321"));

        model.addAttribute("students", students);
        return "bt2";
    }

}
