package com.student.service;

import com.student.dao.CourseDao;
import com.student.entity.Course;
import com.student.exception.CourseNotFoundException;
import com.student.exception.DuplicateResourceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseDao courseDao;

    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public void addCourse(Course course) {

        if (course == null) {
            throw new IllegalArgumentException(
                    "Course cannot be null."
            );
        }

        Course existingCourse =
                courseDao.getCourseByCode(course.getCourseCode());

        if (existingCourse != null) {
            throw new DuplicateResourceException(
                    "Course with code " +
                            course.getCourseCode() +
                            " already exists."
            );
        }

        courseDao.addCourse(course);
    }

    @Override
    public Course getCourseById(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "Invalid course ID."
            );
        }

        Course course =
                courseDao.getCourseById(id);

        if (course == null) {
            throw new CourseNotFoundException(
                    "Course with ID " +
                            id +
                            " not found."
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
            throw new IllegalArgumentException(
                    "Course cannot be null."
            );
        }

        if (course.getId() <= 0) {
            throw new IllegalArgumentException(
                    "Invalid course ID."
            );
        }

        Course existingCourse =
                courseDao.getCourseById(course.getId());

        if (existingCourse == null) {
            throw new CourseNotFoundException(
                    "Course with ID " +
                            course.getId() +
                            " not found."
            );
        }

        Course courseWithSameCode =
                courseDao.getCourseByCode(course.getCourseCode());

        if (courseWithSameCode != null &&
                courseWithSameCode.getId() != course.getId()) {

            throw new DuplicateResourceException(
                    "Course with code " +
                            course.getCourseCode() +
                            " already exists."
            );
        }

        courseDao.updateCourse(course);
    }

    @Override
    public void deleteCourse(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "Invalid course ID."
            );
        }

        Course course =
                courseDao.getCourseById(id);

        if (course == null) {
            throw new CourseNotFoundException(
                    "Course with ID " +
                            id +
                            " not found."
            );
        }

        courseDao.deleteCourse(id);
    }
}