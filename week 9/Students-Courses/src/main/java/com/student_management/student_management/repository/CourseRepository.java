package com.student_management.student_management.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.student_management.student_management.model.Course;
import com.student_management.student_management.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing courses.
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    /**
     * Retrieves a paginated list of all courses.
     *
     * @param pageable Pageable object for pagination
     * @return         Page object containing the courses
     */
    @Query(value = "SELECT * FROM courses", nativeQuery = true)
    Page<Course> fetchAllFromCustom(Pageable pageable);

    /**
     * Filters courses by title.
     *
     * @param title    Title to filter by
     * @param pageable Pageable object for pagination
     * @return         Page object containing the filtered courses
     */
    @Query(value = "SELECT * FROM courses WHERE (:title IS NULL OR LOWER(title) LIKE CONCAT('%', LOWER(:title), '%'))", nativeQuery = true)
    Page<Course> filterCourses(@Param("title") String title, Pageable pageable);

    /**
     * Finds students enrolled in a course.
     *
     * @param courseId ID of the course
     * @return         List of students enrolled in the course
     */
    @Query(value = "SELECT s.* FROM students s JOIN student_course sc ON s.id = sc.student_id " +
            "WHERE sc.course_id = :courseId", nativeQuery = true)
    List<Student> findStudentsByCourseId(@Param("courseId") Long courseId);
}

