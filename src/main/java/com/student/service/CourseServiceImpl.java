package com.student.service;

import com.student.dao.CourseDao;
import com.student.dao.CourseDaoImpl;
import com.student.entity.Course;
import com.student.exception.CourseNotFoundException;

import java.util.List;

public class CourseServiceImpl implements CourseService {

    private final CourseDao courseDao = new CourseDaoImpl();

    @Override
    public void addCourse(Course course) {

        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null.");
        }

        if (course.getCourseName() == null ||
                course.getCourseName().trim().isEmpty()) {

            throw new IllegalArgumentException("Course name is required.");
        }

        if (course.getCourseCode() == null ||
                course.getCourseCode().trim().isEmpty()) {

            throw new IllegalArgumentException("Course code is required.");
        }

        if (course.getDuration() == null ||
                course.getDuration().trim().isEmpty()) {

            throw new IllegalArgumentException("Course duration is required.");
        }

        if (course.getFees() <= 0) {
            throw new IllegalArgumentException(
                    "Course fees must be greater than 0."
            );
        }

        courseDao.addCourse(course);
    }

    @Override
    public Course getCourseById(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("Invalid course ID.");
        }

        Course course = courseDao.getCourseById(id);

        if (course == null) {
            throw new CourseNotFoundException(
                    "Course with ID " + id + " not found."
            );
        }

        return course;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseDao.getAllCourses();
    }

    @Override
    public void updateCourse(Course course) {

        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null.");
        }

        if (course.getId() <= 0) {
            throw new IllegalArgumentException("Invalid course ID.");
        }

        if (course.getCourseName() == null ||
                course.getCourseName().trim().isEmpty()) {

            throw new IllegalArgumentException("Course name is required.");
        }

        if (course.getCourseCode() == null ||
                course.getCourseCode().trim().isEmpty()) {

            throw new IllegalArgumentException("Course code is required.");
        }

        if (course.getDuration() == null ||
                course.getDuration().trim().isEmpty()) {

            throw new IllegalArgumentException("Course duration is required.");
        }

        if (course.getFees() <= 0) {
            throw new IllegalArgumentException(
                    "Course fees must be greater than 0."
            );
        }

        Course existingCourse = courseDao.getCourseById(course.getId());

        if (existingCourse == null) {
            throw new CourseNotFoundException(
                    "Course with ID " + course.getId() + " not found."
            );
        }

        courseDao.updateCourse(course);
    }

    @Override
    public void deleteCourse(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("Invalid course ID.");
        }

        Course course = courseDao.getCourseById(id);

        if (course == null) {
            throw new CourseNotFoundException(
                    "Course with ID " + id + " not found."
            );
        }

        courseDao.deleteCourse(id);
    }
}