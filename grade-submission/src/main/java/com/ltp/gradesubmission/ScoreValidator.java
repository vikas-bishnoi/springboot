package com.ltp.gradesubmission;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class ScoreValidator implements ConstraintValidator<Score, String> {
    List<String> scores = Arrays.asList(
            "A+", "A", "A-",
            "B+", "B", "B-",
            "C+", "C", "C-",
            "D+", "D", "D-",
            "A+"
    );
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        for(String score: scores)
            if(score.equals(value))
                return true;
        return false;
    }
}
