package com.student_management.student_management.controller;

import com.student_management.student_management.model.Course;
import com.student_management.student_management.model.Student;
import com.student_management.student_management.service.CourseService;
import com.student_management.student_management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

/**
 * This controller class handles HTTP requests related to students.
 */
@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    /**
     * Displays a paginated list of students.
     *
     * @param model     Model object to add attributes
     * @param page      Page number
     * @param size      Number of items per page
     * @param sortBy    Sorting field
     * @param direction Sorting direction (asc/desc)
     * @param name      Student name filter
     * @param surname   Student surname filter
     * @param email     Student email filter
     * @param age       Student age filter
     * @param courseId  Course ID filter
     * @return          View name for displaying students
     */
    @GetMapping("")
    public String listStudents(Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "5") int size,
                               @RequestParam(defaultValue = "id") String sortBy,
                               @RequestParam(defaultValue = "asc") String direction,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) String surname,
                               @RequestParam(required = false) String email,
                               @RequestParam(required = false) Integer age,
                               @RequestParam(required = false) Long courseId) {

        Sort.Direction sortDirection = direction.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

        Page<Student> studentsPage;

        if (name != null && !name.isEmpty() || surname != null && !surname.isEmpty() || email != null && !email.isEmpty() || age != null || courseId != null) {
            studentsPage = studentService.filterStudents(pageable, courseId, name, surname, email, age);
            model.addAttribute("name", name);
            model.addAttribute("surname", surname);
            model.addAttribute("email", email);
            model.addAttribute("age", age);
            model.addAttribute("courseId", courseId);

        } else {
            studentsPage = studentService.getAllStudents(pageable);
        }
        model.addAttribute("students", studentsPage.getContent());
        model.addAttribute("totalPages", studentsPage.getTotalPages());
        model.addAttribute("currentPage", studentsPage.getNumber());

        model.addAttribute("sortBy", sortBy);
        model.addAttribute("direction", direction);

        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);

        return "students/index";
    }

    /**
     * Adds a new student.
     *
     * @param student Student object to be added
     * @return        Redirects to the list of students after addition
     */
    @PostMapping("/addStudent")
    public String addStudent(@ModelAttribute Student student) {
        studentService.addStudent(student);
        return "redirect:/students";
    }

    /**
     * Deletes a student by ID.
     *
     * @param id ID of the student to be deleted
     * @return   Redirects to the list of students after deletion
     */
    @GetMapping("/deleteStudent")
    public String deleteStudent(@RequestParam("id") Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }

    /**
     * Updates an existing student.
     *
     * @param id                 ID of the student to be updated
     * @param updatedStudent     Updated Student object
     * @param redirectAttributes RedirectAttributes object for flash attributes
     * @return                   Redirects to the list of students after update
     */
    @PostMapping("/updateStudent/{id}")
    public String updateStudent(@PathVariable("id") Long id, @ModelAttribute Student updatedStudent, RedirectAttributes redirectAttributes) {
        Student existingStudent = studentService.getStudentById(id);
        if (existingStudent == null) {
            return "redirect:/students";
        }

        existingStudent.setName(updatedStudent.getName());
        existingStudent.setSurname(updatedStudent.getSurname());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setAge(updatedStudent.getAge());

        studentService.addStudent(existingStudent);

        return "redirect:/students";
    }

    /**
     * Displays the courses of a student.
     *
     * @param model Model object to add attributes
     * @param id    ID of the student
     * @return      View name for displaying student courses
     */
    @GetMapping("/{id}/courses")
    public String showStudentCourses(Model model, @PathVariable("id") Long id) {
        try {
            Student student = studentService.getStudentById(id);
            List<Course> courses = courseService.getAllCourses();
            model.addAttribute("student", student);
            model.addAttribute("courses", courses);

        } catch (Exception e) {
            model.addAttribute("error", "Student not found");
        }
        return "students/student_courses";
    }

    /**
     * Adds a course to a student.
     *
     * @param studentId ID of the student
     * @param courseId  ID of the course to be added
     * @return          Redirects to the student courses page after addition
     */
    @PostMapping("/{studentId}/addCourse")
    public String addCourseToStudent(@PathVariable("studentId") Long studentId, @RequestParam("courseId") Long courseId) {
        try {
            Student student = studentService.getStudentById(studentId);
            Course course = courseService.getCourseById(courseId);
            if (student != null && course != null) {
                student.getCourses().add(course);
                studentService.addStudent(student);
            }
        } catch (Exception e) {
        }
        return "redirect:/students/" + studentId + "/courses";
    }

    /**
     * Removes a course from a student.
     *
     * @param studentId ID of the student
     * @param courseId  ID of the course to be removed
     * @return          Redirects to the student courses page after removal
     */
    @PostMapping("/{studentId}/removeCourse")
    public String removeCourseFromStudent(@PathVariable("studentId") Long studentId, @RequestParam("courseId") Long courseId) {
        try {
            Student student = studentService.getStudentById(studentId);
            Course course = courseService.getCourseById(courseId);
            if (student != null && course != null) {
                student.getCourses().remove(course);
                studentService.addStudent(student);
            }
        } catch (Exception e) {
        }
        return "redirect:/students/" + studentId + "/courses";
    }
}

