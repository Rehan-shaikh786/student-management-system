package com.student.dao;

import com.student.entity.Course;

import java.util.List;

public interface CourseDao {

    void addCourse(Course course);

    Course getCourseById(int id);

    List<Course> getAllCourses();

    void updateCourse(Course course);

    void deleteCourse(int id);

    Course getCourseByCode(String courseCode);
}