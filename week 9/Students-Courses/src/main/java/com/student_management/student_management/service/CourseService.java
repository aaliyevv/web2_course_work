package com.student_management.student_management.service;

import com.student_management.student_management.model.Course;
import com.student_management.student_management.model.Student;
import com.student_management.student_management.repository.CourseRepository;
import com.student_management.student_management.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
    public Page<Course> searchCourses(String title, Pageable pageable) {
        return courseRepository.filterCourses(title, pageable);
    }
    public Page<Course> showCourses(Pageable pageable) {
        return courseRepository.fetchAllFromCustom(pageable);
    }
    public void saveCourse(Course course) {
        courseRepository.save(course);
    }
    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }
    public void updateCourse(Long id, Course updatedCourse) {
        Course course = getCourseById(id);
        if (course != null) {
            course.setTitle(updatedCourse.getTitle());
            course.setDescription(updatedCourse.getDescription());
            courseRepository.save(course);
        }
    }
    public List<Student> getStudentsByCourseId(Long courseId) {
        return studentRepository.findStudentsByCourseId(courseId);
    }
    public void addStudentToCourse(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        if (course == null) {
            throw new IllegalArgumentException("Invalid course ID: " + courseId);
        }

        Student student = studentRepository.findById(studentId).orElse(null);
        if (student == null) {
            throw new IllegalArgumentException("Invalid student ID: " + studentId);
        }


        student.getCourses().add(course);

        studentRepository.save(student);
    }
}