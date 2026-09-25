package com.student.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "enrollment")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Valid
    @NotNull(message = "Student is required")
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Valid
    @NotNull(message = "Course is required")
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    public Enrollment() {
    }

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", student=" + student +
                ", course=" + course +
                '}';
    }
}