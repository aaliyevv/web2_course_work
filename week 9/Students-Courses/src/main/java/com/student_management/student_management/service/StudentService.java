package com.student_management.student_management.service;

import com.student_management.student_management.model.Course;
import com.student_management.student_management.model.Student;
import com.student_management.student_management.repository.CourseRepository;
import com.student_management.student_management.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<Student> showAllStudents() {
        return studentRepository.findAll();
    }
    public Page<Student> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }
    public Page<Student> filterStudents(Pageable pageable, Long courseId, String name, String surname, String email, Integer age) {
        if (courseId == null){
            return studentRepository.filterStudents(name, surname, email, age, pageable);
        }
        return studentRepository.filterStudentsByCourse(courseId,
                name, surname, email, age, pageable);
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }
    @Transactional
    public void removeCourseFromStudent(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new EntityNotFoundException("Student not found"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new EntityNotFoundException("Course not found"));

        student.getCourses().remove(course);
        studentRepository.save(student);
    }
}