package com.student_management.student_management.controller;

import com.student_management.student_management.model.Course;
import com.student_management.student_management.model.Student;
import com.student_management.student_management.service.StudentService;
import com.student_management.student_management.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.List;

/**
 * This controller class handles HTTP requests related to courses.
 */
@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    /**
     * Displays a paginated list of courses.
     *
     * @param model     Model object to add attributes
     * @param page      Page number
     * @param size      Number of items per page
     * @param sortBy    Sorting field
     * @param direction Sorting direction (asc/desc)
     * @param search    Keyword for searching courses
     * @return          View name for displaying courses
     */
    @GetMapping("")
    public String showCourses(Model model,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "5") int size,
                              @RequestParam(defaultValue = "title") String sortBy,
                              @RequestParam(defaultValue = "asc") String direction,
                              @RequestParam(defaultValue = "") String search) {

        Sort.Direction sortDirection = direction.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
        Page<Course> coursesPage;

        if (!search.isEmpty()) {
            coursesPage = courseService.searchCourses(search, pageable);
            model.addAttribute("search", search);
        } else {
            coursesPage = courseService.showCourses(pageable);
        }

        model.addAttribute("courses", coursesPage.getContent());
        model.addAttribute("totalPages", coursesPage.getTotalPages());
        model.addAttribute("currentPage", coursesPage.getNumber());
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("direction", direction);

        return "courses/index";
    }

    /**
     * Displays the form for creating a new course.
     *
     * @param model Model object to add attributes
     * @return      View name for creating a new course
     */
    @GetMapping("/create_course")
    public String showCreateCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "courses/create_course";
    }

    /**
     * Handles the submission of the form for creating a new course.
     *
     * @param course    Course object to be created
     * @return          Redirects to the list of courses after creation
     */
    @PostMapping("/create_course")
    public String createCourse(@ModelAttribute("course") Course course) {
        courseService.saveCourse(course);
        return "redirect:/courses";
    }

    /**
     * Deletes a course by its ID.
     *
     * @param courseId  ID of the course to be deleted
     * @return          Redirects to the list of courses after deletion
     */
    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable("id") Long courseId) {
        courseService.deleteCourse(courseId);
        return "redirect:/courses";
    }

    /**
     * Displays the form for editing an existing course.
     *
     * @param id    ID of the course to be edited
     * @param model Model object to add attributes
     * @return      View name for editing the course
     */
    @GetMapping("/edit/{id}")
    public String showEditCourseForm(@PathVariable("id") Long id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        return "courses/edit-course";
    }

    /**
     * Handles the submission of the form for editing an existing course.
     *
     * @param id        ID of the course to be edited
     * @param course    Course object with updated information
     * @return          Redirects to the list of courses after editing
     */
    @PostMapping("/edit/{id}")
    public String editCourse(@PathVariable("id") Long id, @ModelAttribute("course") Course course) {
        courseService.updateCourse(id, course);
        return "redirect:/courses";
    }

    /**
     * Displays detailed information about a course.
     *
     * @param courseId  ID of the course to display details for
     * @param model     Model object to add attributes
     * @return          View name for displaying course details
     */
    @GetMapping("/detail/{id}")
    public String getCourseDetail(@PathVariable("id") Long courseId, Model model) {
        Course course = courseService.getCourseById(courseId);
        if (course == null) {
            return "error";
        }

        List<Student> students = courseService.getStudentsByCourseId(courseId);
        List<Student> allStudents = studentService.showAllStudents();
        model.addAttribute("course", course);
        model.addAttribute("students", students);
        model.addAttribute("allStudents", allStudents);

        return "courses/course-detail";
    }

    /**
     * Removes a course from a student's list of enrolled courses.
     *
     * @param studentId ID of the student
     * @param courseId  ID of the course to be removed
     * @return          Redirects to the course detail page after removal
     */
    @GetMapping("/{courseId}/students/{studentId}/remove")
    public String removeCourseFromStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
        studentService.removeCourseFromStudent(studentId, courseId);
        return "redirect:/courses/detail/{courseId}";
    }

    /**
     * Adds a student to a course.
     *
     * @param courseId  ID of the course
     * @param studentId ID of the student to be added
     * @return          Redirects to the course detail page after addition
     */
    @PostMapping("/{courseId}/addStudent")
    public String addStudentToCourse(@PathVariable Long courseId, @RequestParam Long studentId) {
        courseService.addStudentToCourse(courseId, studentId);
        return "redirect:/courses/detail/{courseId}";
    }
}
