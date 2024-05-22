

package com.student_management.student_management.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.student_management.student_management.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Repository interface for managing students.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * Retrieves a list of all students.
     *
     * @return List of all students
     */
    @Query(value = "SELECT * FROM students", nativeQuery = true)
    List<Student> fetchAllFromCustom();

    /**
     * Filters students by name, surname, email, and age.
     *
     * @param name     Name to filter by
     * @param surname  Surname to filter by
     * @param email    Email to filter by
     * @param age      Age to filter by
     * @param pageable Pageable object for pagination
     * @return         Page object containing the filtered students
     */
    @Query(value = "SELECT * FROM students WHERE (:name IS NULL OR name LIKE %:name%) " +
            "AND (:surname IS NULL OR surname LIKE %:surname%) " +
            "AND (:email IS NULL OR email LIKE %:email%) " +
            "AND (:age IS NULL OR age = :age)",
            nativeQuery = true)
    Page<Student> filterStudents(String name, String surname, String email, Integer age, Pageable pageable);

    /**
     * Filters students by name, surname, email, and age for a specific course.
     *
     * @param courseId ID of the course
     * @param name     Name to filter by
     * @param surname  Surname to filter by
     * @param email    Email to filter by
     * @param age      Age to filter by
     * @param pageable Pageable object for pagination
     * @return         Page object containing the filtered students
     */
    @Query(value = "SELECT * FROM students s INNER JOIN student_course sc ON s.id = sc.student_id WHERE sc.course_id = :courseId AND (:name IS NULL OR s.name LIKE %:name%) AND (:surname IS NULL OR s.surname LIKE %:surname%) AND (:email IS NULL OR s.email LIKE %:email%) AND (:age IS NULL OR s.age = :age)",
            countQuery = "SELECT COUNT(*) FROM students s INNER JOIN student_course sc ON s.id = sc.student_id WHERE sc.course_id = :courseId AND (:name IS NULL OR s.name LIKE %:name%) AND (:surname IS NULL OR s.surname LIKE %:surname%) AND (:email IS NULL OR s.email LIKE %:email%) AND (:age IS NULL OR s.age = :age)",
            nativeQuery = true)
    Page<Student> filterStudentsByCourse(Long courseId, String name, String surname, String email, Integer age, Pageable pageable);

    /**
     * Finds students enrolled in a course.
     *
     * @param courseId ID of the course
     * @return         List of students enrolled in the course
     */
    @Query(value = "SELECT * FROM students s JOIN student_course sc ON s.id = sc.student_id " +
            "WHERE sc.course_id = :courseId", nativeQuery = true)
    List<Student> findStudentsByCourseId(@Param("courseId") Long courseId);
}
