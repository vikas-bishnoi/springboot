package com.ltp.gradesubmission;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class GradeController {
    List<Grade> studentGrades = new ArrayList<>();

    @GetMapping("/grades")
    public String getGrades(Model model) {
        model.addAttribute("grades", studentGrades);
        return "grades";
    }

    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required = false) String id) {
        int index = getGradeIndex(id);
        Grade grade = new Grade();
        if(index != -1)
            grade = studentGrades.get(index);

        model.addAttribute("grade", grade);
        return "form";
    }

    @PostMapping("/handle-submit")
    public String submitGrade(Grade grade) {
        int index = getGradeIndex(grade.getID());
        if(index == -1) {
            studentGrades.add(grade);
        } else {
            studentGrades.set(index, grade);
        }
        return "redirect:/grades";
    }

    public Integer getGradeIndex(String id) {
        for(int i=0; i<studentGrades.size(); i++) {
            if(studentGrades.get(i).getID().equals(id))
                return i;
        }
        return -1;
    }
}