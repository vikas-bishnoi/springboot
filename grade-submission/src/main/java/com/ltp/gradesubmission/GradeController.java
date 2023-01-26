package com.ltp.gradesubmission;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class GradeController {
    List<Grade> studentGrades = Arrays.asList(
        new Grade("Harry", "Potions", "C-"),
        new Grade("Ron", "Charms", "C"),
        new Grade("Hermoine", "Potions", "A")
    );

    @GetMapping("/grades")
    public String getGrades(Model model) {
        model.addAttribute("grades", studentGrades);
        return "grades";
    }
}