package com.ltp.gradesubmission;

import com.ltp.gradesubmission.repository.GradeRepository;
import com.ltp.gradesubmission.service.GradeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GradeServiceTest {
    @Mock
    GradeRepository gradeRepository;

    @InjectMocks
    private GradeService gradeService;

    @Test
    public void getGradesFromRepoTest() {
        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(
            new Grade("Harry", "Potions", "C"),
            new Grade("Ron", "Charms", "A")
        ));

        List<Grade> grades = gradeService.getGrades();

        assertEquals("Harry", grades.get(0).getName());
        assertEquals("Charms", grades.get(1).getSubject());
    }

    @Test
    public void getGradeIndexTest() {
        Grade grade = new Grade("Harry", "Potions", "C");
        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(grade));
        when(gradeRepository.getGrade(0)).thenReturn(grade);

        int valid = gradeService.getGradeIndex(grade.getID());
        int notFound = gradeService.getGradeIndex("abc");

        assertEquals(0, valid);
        assertEquals(Constants.NOT_FOUND, notFound);
    }

    @Test
    public void getGradeByIdTest() {
        Grade grade = new Grade("Harry", "Potions", "C");
        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(grade));
        when(gradeRepository.getGrade(0)).thenReturn(grade);

        String id = grade.getID();

        Grade result = gradeService.getGradeById(id);

        assertEquals(grade, result);
    }
}
