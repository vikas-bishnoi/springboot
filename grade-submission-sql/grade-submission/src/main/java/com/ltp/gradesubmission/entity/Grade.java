package com.ltp.gradesubmission.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "grade")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="score", nullable = false)
    private String score;

    @ManyToOne(optional = false)
    @JoinColumn(name="student_id", referencedColumnName = "id")
    private Student student;

    @ManyToOne(optional = false)
    @JoinColumn(name="course_id", referencedColumnName = "id")
    private Course course;
}
