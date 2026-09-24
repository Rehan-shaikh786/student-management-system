package com.student.dao;

import com.student.entity.Course;

import java.util.List;

public interface CourseDao {

    // Create
    void addCourse(Course course);

    // Read
    Course getCourseById(int id);

    List<Course> getAllCourses();

    // Update
    void updateCourse(Course course);

    // Delete
    void deleteCourse(int id);
}