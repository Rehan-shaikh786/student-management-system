package com.student.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Course name is required")
    @Column(name = "course_name", nullable = false)
    private String courseName;

    @NotBlank(message = "Course code is required")
    @Column(name = "course_code", unique = true)
    private String courseCode;

    @NotBlank(message = "Course duration is required")
    @Column(name = "duration")
    private String duration;

    @DecimalMin(value = "0.0", inclusive = false,
            message = "Course fees must be greater than 0")
    @Column(name = "fees")
    private double fees;

    public Course() {
    }

    public Course(String courseName, String courseCode,
                  String duration, double fees) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.duration = duration;
        this.fees = fees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", duration='" + duration + '\'' +
                ", fees=" + fees +
                '}';
    }
}