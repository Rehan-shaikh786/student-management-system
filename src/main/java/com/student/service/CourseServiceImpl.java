package com.student.service;

import com.student.dao.CourseDao;
import com.student.dao.CourseDaoImpl;
import com.student.entity.Course;

import java.util.List;

public class CourseServiceImpl implements CourseService {

    private final CourseDao courseDao = new CourseDaoImpl();

    @Override
    public void addCourse(Course course) {

        if (course == null) {
            System.out.println("Course cannot be null.");
            return;
        }

        if (course.getCourseName() == null ||
                course.getCourseName().trim().isEmpty()) {

            System.out.println("Course name is required.");
            return;
        }

        if (course.getCourseCode() == null ||
                course.getCourseCode().trim().isEmpty()) {

            System.out.println("Course code is required.");
            return;
        }

        if (course.getDuration() == null ||
                course.getDuration().trim().isEmpty()) {

            System.out.println("Course duration is required.");
            return;
        }

        if (course.getFees() <= 0) {
            System.out.println("Course fees must be greater than 0.");
            return;
        }

        courseDao.addCourse(course);
    }

    @Override
    public Course getCourseById(int id) {

        if (id <= 0) {
            System.out.println("Invalid course ID.");
            return null;
        }

        return courseDao.getCourseById(id);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseDao.getAllCourses();
    }

    @Override
    public void updateCourse(Course course) {

        if (course == null) {
            System.out.println("Course cannot be null.");
            return;
        }

        if (course.getId() <= 0) {
            System.out.println("Invalid course ID.");
            return;
        }

        if (course.getCourseName() == null ||
                course.getCourseName().trim().isEmpty()) {

            System.out.println("Course name is required.");
            return;
        }

        if (course.getCourseCode() == null ||
                course.getCourseCode().trim().isEmpty()) {

            System.out.println("Course code is required.");
            return;
        }

        if (course.getFees() <= 0) {
            System.out.println("Course fees must be greater than 0.");
            return;
        }

        courseDao.updateCourse(course);
    }

    @Override
    public void deleteCourse(int id) {

        if (id <= 0) {
            System.out.println("Invalid course ID.");
            return;
        }

        courseDao.deleteCourse(id);
    }
}