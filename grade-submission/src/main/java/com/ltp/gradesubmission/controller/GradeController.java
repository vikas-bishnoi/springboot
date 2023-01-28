package com.ltp.gradesubmission.controller;

import com.ltp.gradesubmission.Constants;
import com.ltp.gradesubmission.Grade;
import com.ltp.gradesubmission.repository.GradeRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class GradeController {
    GradeRepository gradeRepository = new GradeRepository();
    @GetMapping("/grades")
    public String getGrades(Model model) {
        model.addAttribute("grades", gradeRepository.getGrades());
        return "grades";
    }

    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required = false) String id) {
        int index = getGradeIndex(id);
        model.addAttribute("grade", index == Constants.NOT_FOUND ? new Grade() : gradeRepository.getGrade(index));
        return "form";
    }

    @PostMapping("/handle-submit")
    public String submitGrade(@Valid Grade grade, BindingResult result) {
        if(result.hasErrors()) {
                return  "form";
        }

        int index = getGradeIndex(grade.getID());
        if(index == Constants.NOT_FOUND) {
            gradeRepository.addGrade(grade);
        } else {
            gradeRepository.updateGrade(grade, index);
        }
        return "redirect:/grades";
    }

    public Integer getGradeIndex(String id) {
        for(int i=0; i<gradeRepository.getGrades().size(); i++) {
            if(gradeRepository.getGrades().get(i).getID().equals(id))
                return i;
        }
        return Constants.NOT_FOUND;
    }
}